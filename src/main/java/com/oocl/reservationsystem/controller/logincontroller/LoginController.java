package com.oocl.reservationsystem.controller.logincontroller;

import com.oocl.reservationsystem.dto.logindto.LoginRequest;
import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.dto.logindto.RegisterRequest;
import com.oocl.reservationsystem.service.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final LoginService loginService;

  @Autowired
  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  @ResponseBody
  public LoginResponse customerLogin(@RequestBody LoginRequest loginRequest) {
    return loginService.getCustomerLoginRequest(loginRequest);
  }

  @PostMapping("/register")
  public void customerLogin(@RequestBody RegisterRequest registerRequest) {

  }
}
