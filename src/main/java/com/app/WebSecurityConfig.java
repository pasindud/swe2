// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package com.app;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.web.savedrequest.NullRequestCache;

// /**
//  *
//  * @author Pasindu
//  */

// @EnableWebSecurity
// class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http.authorizeRequests() //
// 				.anyRequest().authenticated() //
// 				.and().requestCache().requestCache(new NullRequestCache()) //
// 				.and().httpBasic() //
// 				.and().csrf().disable();
// 	}

// 	@Autowired
// 	void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// 		auth.inMemoryAuthentication() //
// 				.withUser("user").password("password").authorities("ROLE_USER") //
// 				.and() //
// 				.withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
// 	}
// }

package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests() //
        .anyRequest()
        .authenticated() //
        .and()
        .requestCache()
        .requestCache(new NullRequestCache()) //
        .and()
        .httpBasic() //
        .and()
        .csrf()
        .disable();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  // @Autowired
  // void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  // 	auth.inMemoryAuthentication() //
  // 			.withUser("user").password("password").authorities("ROLE_USER") //
  // 			.and() //
  // 			.withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
  // }
}
