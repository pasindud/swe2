package com.app.config;

import com.google.common.collect.ImmutableList;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private UserDetailsService userDetailsService;

  final CorsConfiguration configuration = new CorsConfiguration();

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {

    configuration.setAllowedOrigins(ImmutableList.of("*"));
    configuration.setAllowedMethods(
        ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(
        ImmutableList.of("x-auth-token", "Authorization", "Cache-Control", "Content-Type"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/api/registration", "/resources/", "/resources/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .requestCache()
        .requestCache(new NullRequestCache())
        .and()
        .cors()
        .and()
        .csrf()
        .disable()
        .httpBasic();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // HARD coding for testing.
    //    auth.inMemoryAuthentication() //
    //        .withUser("user")
    //        .password("password")
    //        .authorities("ROLE_USER") //
    //        .and() //
    //        .withUser("admin")
    //        .password("password")
    //        .authorities("ROLE_USER", "ROLE_ADMIN");
    //
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }
}
