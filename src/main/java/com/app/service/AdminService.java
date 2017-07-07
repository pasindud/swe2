package com.app.service;

import com.app.controller.AdminController;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.Utils.FreqAmount;

/**
 * Created by Pasindu on 7/7/17.
 */
@Service
public class AdminService {
  private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;
  /** Query to get the freq of different amounts. */
  String QUERY_FOR_AMOUNT_FREQ = "SELECT round(`amount`,2) as 'amounts',count(*) as freq  FROM Transaction GROUP by round(`amount`,2)";

  private List <FreqAmount> getFreqTransactionAmounts() {
    List<FreqAmount> freqAmountList = new ArrayList<>();
    List<Map<String, Object>> rows = jdbcTemplate.queryForList(QUERY_FOR_AMOUNT_FREQ);

    for (Map row : rows) {
      FreqAmount freqAmount = new FreqAmount();
      freqAmount.amounts = (double)row.get("amounts") ;
      freqAmount.freq = (long) row.get("freq") ;
      freqAmountList.add(freqAmount);
      logger.info(String.format("Amount - %s | Freq - %s", freqAmount.amounts, freqAmount.freq));
    }
    return freqAmountList;
  }

  /**
   * Run linear regression on the data and find outlier amount.
   * @return the amount that is a outlier.
   */
  public double analyzeAmounts() {
    List <FreqAmount>  freqAmountList = getFreqTransactionAmounts();
    Map<Double, Double> errors = new HashMap <>();

    errors.put(runLinearRegression(freqAmountList, null), -1d);

    for (FreqAmount freqAmount : freqAmountList) {
      double error = runLinearRegression(freqAmountList, new Point(freqAmount.amounts, freqAmount.freq));
      errors.put(error, freqAmount.amounts);
    }

    double totalError = errors.get(freqAmountList.get(0).amounts);
    double amountWithout = freqAmountList.get(0).amounts;

    for (Map.Entry<Double, Double> entry: errors.entrySet()) {
      logger.info("Scores for each amount - " + entry.getValue() + " - score - " + entry.getKey());

      if (totalError > entry.getValue()) {
        totalError = entry.getKey();
        amountWithout = entry.getValue();
      }
    }
    return amountWithout;
  }

  private double runLinearRegression(List <FreqAmount> freqAmountList, Point pointToRemove) {
    double tolalError = 0;
    SimpleRegression simpleRegression = new SimpleRegression();

    for(int i = 0; i < freqAmountList.size(); i++) {
      simpleRegression.addData(freqAmountList.get(i).amounts, freqAmountList.get(i).freq);
    }

    if (pointToRemove != null) {
      simpleRegression.removeData(pointToRemove.x, pointToRemove.y);
    }

    Point a = new Point(10d, simpleRegression.predict(10));
    Point b = new Point(20d, simpleRegression.predict(20));

    for (FreqAmount freqAmount: freqAmountList){
      if (pointToRemove == null || freqAmount.amounts != pointToRemove.x) {
        Point c = new Point(freqAmount.amounts, freqAmount.freq);
        Point p = pointToProjectLine(a, b, c);
        double dist = distance(c,p);
        tolalError += dist;
      }
    }
    logger.info("Total Error - " + tolalError);
    return tolalError;
  }

  private double distance(Point a, Point b) {
    Point p = new Point(a.x - b.x, a.y - b.y);
    return Math.sqrt(dot(p, p))
            ;
  }

  private Point pointToProjectLine(Point a, Point b, Point c) {
    double R = dot(new Point(c.x - a.x, c.y-a.y), new Point(b.x - a.x, b.y-a.y)) /
            dot(new Point(b.x - a.x, b.y-a.y), new Point(b.x - a.x, b.y-a.y));
    Point p = new Point(new Point(b.x - a.x, b.y-a.y).x * R, new Point(b.x - a.x, b.y-a.y).y * R);
    return  new Point(p.x + a.x, p.y + a.y);
  }

  private double dot(Point p, Point q) {
    return p.x*q.x+p.y*q.y;
  }

  private class Point{
    public double x;
    public double y;
    Point(double x, double y) {
      this.x = x ;
      this.y = y;
    }
  }
}
