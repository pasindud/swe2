package com.app.controller;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * Class used for API endpoints for admin operations.
 */
@RestController
public class AdminController {
  /**
   * Activate and deactivate users.
   * @param userId the id of user to toggle status of.
   * @param activate whether status should be active or in active.
   * @return whether the operation was successful or not,
   */
  @RequestMapping("/api/admin/change_user_status")
  public boolean changeUserStatus(@RequestParam("user_id") int userId, @RequestParam("activate") boolean activate) {
    return false;
  }
}
