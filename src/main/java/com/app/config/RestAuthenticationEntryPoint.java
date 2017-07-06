package com.app.config;

import com.app.enties.LoginHistory;
import com.app.repository.LoginHistoryRepository;
import com.app.service.LoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

/**
 * Created by Pasindu on 7/2/17.
 */
@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Autowired
  LoginHistoryService loginHistoryService;
  @Autowired
  LoginHistoryRepository loginHistoryRepository;

  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authenticationException) throws IOException, ServletException {
    String username = getUserNameFromAuthorizationHeader(request.getHeader("Authorization"));

    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    // Incorrect auth token.
    if (username.equals("")) {
      response.getOutputStream().println("{ \"error\": \""+ authenticationException.getMessage() +".\" }");
      return;
    }

    List<LoginHistory> loginHistories = loginHistoryRepository.getLast10MinsLoginHistoryByUsername(username);


    if (loginHistories.size() >= 3) {
      response.getOutputStream().println("{ \"error\": \"Account temporary locked due to multiple incorrect attempts.\" }");
    } else {
      loginHistoryService.logUserLogginHistory(0, username);
      response.getOutputStream().println("{ \"error\": \"" + authenticationException.getMessage() + "\" }");
    }
  }

  private String getUserNameFromAuthorizationHeader(String authorizationHeader) {
    if (authorizationHeader == null || authorizationHeader.equals("")) {
      return "";
    }
    String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
    String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
    return credentials.split(":",2)[0];
  }
}
