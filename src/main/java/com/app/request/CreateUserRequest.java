/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.request;

import com.app.enties.Customer;
import com.app.enties.Users;

/** Class used to register the user. */
public class CreateUserRequest {
  private Users users;
  private Customer customer;

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
}
