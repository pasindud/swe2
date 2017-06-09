/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 *
 * <pre>{@code
 * curl --noproxy localhost -u user:password -v http://localhost:8080/api/auth
 * export AUTH_TOKEN=...
 * curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v http://localhost:8080/api/greet
 * curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v -d "amount=42.0"  http://localhost:8080/api/order
 * curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v -d "amount=1000.0"  http://localhost:8080/api/order
 *
 * curl --noproxy localhost -u admin:password -v http://localhost:8080/api/auth
 * export AUTH_TOKEN=...
 * curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v -d "amount=1000.0"  http://localhost:8080/api/order
 *
 * }</pre>
 */
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
