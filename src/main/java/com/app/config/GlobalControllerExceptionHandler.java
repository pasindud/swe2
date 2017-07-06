package com.app.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pasindu on 7/6/17.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
//  @ExceptionHandler
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String>  defaultErrorHandler(HttpServletRequest request, Exception e) {
    Map<String, String> response = new HashMap <>();
    response.put("error", "Error occurred try again.");
    return response;
  }
}
