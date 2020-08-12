package com.oocl.reservationsystem.controller.logincontroller;

import com.oocl.reservationsystem.dto.logindto.LoginRequest;
import com.oocl.reservationsystem.dto.logindto.RegisterRequest;
import com.oocl.reservationsystem.dto.orderdto.OrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

  @PostMapping("/login")
  public void customerLogin(@RequestBody LoginRequest loginRequest) {

  }

  @PostMapping("/register")
  public void customerLogin(@RequestBody RegisterRequest registerRequest) {

  }
}
