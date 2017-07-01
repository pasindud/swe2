package com.app.enties;

import javax.persistence.*;

/**
 * Created by Pasindu on 7/2/17.
 */
@Entity
@Table(name = "security_answers")
public class SecurityAnswers {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Integer id;

  private String answer;


  public void setUserId(int userId) {
    this.userId = userId;
  }

  private int userId;

//  @ManyToOne(optional = false, targetEntity = Users.class)
//  @JoinColumn(name = "USERID", referencedColumnName = "USERID")
//  private Users userId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public SecurityQuestions getSecurityQuestions() {
    return securityQuestions;
  }

  public void setSecurityQuestions(SecurityQuestions securityQuestions) {
    this.securityQuestions = securityQuestions;
  }

  @ManyToOne(optional = false, targetEntity = SecurityQuestions.class)
  @JoinColumn(name = "securityquestionsid", referencedColumnName = "ID")
  private SecurityQuestions securityQuestions;

}
