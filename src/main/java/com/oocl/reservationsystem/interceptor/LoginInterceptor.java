//package com.oocl.reservationsystem.interceptor;
//
//import com.oocl.reservationsystem.dto.logindto.LoginResponse;
//import com.oocl.reservationsystem.exception.user.CustomerNotLoginException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//      throws Exception {
//    HttpSession session = request.getSession();
//    LoginResponse user = (LoginResponse) session.getAttribute("user");
//    if (user != null) {
//      return true;
//    } else {
//      throw new CustomerNotLoginException();
//    }
//  }
//}