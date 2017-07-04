/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.request;

import com.app.enties.Customer;
import com.app.enties.Merchant;
import com.app.enties.SecurityAnswers;
import com.app.enties.Users;
import java.util.List;

/** Class used to register the user. */
public class CreateUserRequest {
  private Users users;
  private Customer customer;
  private Merchant merchant;
  private List<SecurityAnswers> answers;

  public Users getUsers() {
    return users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }

  public Users getUser() {
    return this.users;
  }

  public Customer getCustomer() {
    return this.customer;
  }

    public List<SecurityAnswers> getAnswers() {
        return answers;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAnswers(List<SecurityAnswers>answers) {
        this.answers = answers;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
  
}
