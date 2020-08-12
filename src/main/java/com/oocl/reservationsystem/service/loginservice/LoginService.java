package com.oocl.reservationsystem.service.loginservice;

import com.oocl.reservationsystem.dto.logindto.LoginRequest;
import com.oocl.reservationsystem.dto.logindto.LoginResponse;

public interface LoginService {

  LoginResponse getCustomerLoginRequest(LoginRequest loginRequest);
}
