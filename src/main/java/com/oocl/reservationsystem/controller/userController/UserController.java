package com.oocl.reservationsystem.controller.userController;

import com.oocl.reservationsystem.dto.userdto.UserDto;
import com.oocl.reservationsystem.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping()
  public UserDto addOrder(@RequestBody @Valid UserDto userDto) {
    return userService.updateUserInfo(userDto);
  }

}
