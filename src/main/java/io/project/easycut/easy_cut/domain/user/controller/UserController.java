package io.project.easycut.easy_cut.domain.user.controller;

import io.project.easycut.easy_cut.domain.user.dto.request.UserCreateDto;
import io.project.easycut.easy_cut.domain.user.dto.response.UserResponse;
import io.project.easycut.easy_cut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;

  // 유저 회원가입
  @PostMapping("/signup")
  public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserCreateDto userCreateDto) {

    UserResponse userResponse = userService.createUser(userCreateDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(userResponse);
  }
}
