package com.app.enties;

import javax.persistence.*;

/**
 * Created by Pasindu on 7/2/17.
 */

@Entity
@Table(name = "security_questions")
public class SecurityQuestions {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Integer id;

  private String question;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }
}
