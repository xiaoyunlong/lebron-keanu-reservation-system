package com.oocl.reservationsystem.dto.logindto;

import javax.validation.constraints.NotBlank;

public class RegisterRequest {

  @NotBlank
  private String email;
  @NotBlank
  private String password;
  @NotBlank
  private String username;
  @NotBlank
  private String phoneNumber;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
