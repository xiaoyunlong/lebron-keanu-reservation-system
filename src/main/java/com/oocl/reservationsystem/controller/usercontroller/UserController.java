package com.oocl.reservationsystem.controller.usercontroller;

import com.oocl.reservationsystem.dto.userdto.UserDto;
import com.oocl.reservationsystem.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PutMapping()
  public UserDto addOrder(@RequestBody @Valid UserDto userDto) {
    return userService.updateUserInfo(userDto);
  }

}
