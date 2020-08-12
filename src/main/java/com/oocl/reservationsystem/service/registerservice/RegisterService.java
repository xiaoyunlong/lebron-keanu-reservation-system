package com.oocl.reservationsystem.service.registerservice;

import com.oocl.reservationsystem.dto.logindto.LoginResponse;
import com.oocl.reservationsystem.dto.logindto.RegisterRequest;

public interface RegisterService {

  LoginResponse getCustomerRegisterRequest(RegisterRequest registerRequest);
}
