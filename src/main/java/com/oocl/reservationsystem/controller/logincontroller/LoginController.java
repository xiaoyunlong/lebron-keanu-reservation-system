package com.oocl.reservationsystem.controller.logincontroller;

import com.oocl.reservationsystem.dto.logindto.LoginRequest;
import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.dto.logindto.RegisterRequest;
import com.oocl.reservationsystem.service.loginservice.LoginService;
import com.oocl.reservationsystem.service.registerservice.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final LoginService loginService;

  private final RegisterService registerService;

  @Autowired
  public LoginController(LoginService loginService,
      RegisterService registerService) {
    this.loginService = loginService;
    this.registerService = registerService;
  }


  @PostMapping("/login")
  @ResponseBody
  @ResponseStatus(HttpStatus.ACCEPTED)
  public LoginResponse customerLogin(@RequestBody LoginRequest loginRequest) {
    return loginService.getCustomerLoginRequest(loginRequest);
  }

  @PostMapping("/register")
  @ResponseBody
  @ResponseStatus(HttpStatus.ACCEPTED)
  public LoginResponse customerRegister(@RequestBody RegisterRequest registerRequest) {
    return registerService.getCustomerRegisterRequest(registerRequest);
  }
}
