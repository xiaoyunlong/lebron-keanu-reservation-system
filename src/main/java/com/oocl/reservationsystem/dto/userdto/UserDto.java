package com.oocl.reservationsystem.dto.userdto;

import javax.validation.constraints.NotBlank;

public class UserDto {

  @NotBlank
  private String email;
  @NotBlank
  private String phoneNumber;
  @NotBlank
  private String userName;

  public UserDto(String email, String phoneNumber, String userName) {
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
