package com.app.service;

import com.app.Exceptions.ServerException;
import com.app.Exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * The class used to get currency exchange rates.
 */
public class ExchangeRates {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeRates.class);
    /** Yahoo finance exchange rate API format. */
    private static String API_URL = "http://download.finance.yahoo.com/d/quotes.csv?s=%s%s=X&f=l1";

    /**
     * @param fromCurrency the currency to check exchange rate for.
     * @param toCurrency   the currency to check exchange rate for.
     * @return if sucessfully it returns the exchange rate for the given currencies or throws the relavent exception.
     **/
    public static float getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String callingUrl = String.format(API_URL, fromCurrency, toCurrency);
        URL url = null;
        float exchangeRate = -1;

        try {
            url = new URL(callingUrl);
            URLConnection uc = url.openConnection();
            logger.info("Calling API - " + callingUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            exchangeRate = Float.parseFloat(in.readLine());
            logger.info(String.format("Exchange rate for %s to %s is %f",  fromCurrency, toCurrency, exchangeRate));
            return exchangeRate;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new UserException("Incorrect currency");
        }
    }

}
