package io.project.easycut.easy_cut.domain.user.service;

import io.project.easycut.easy_cut.domain.user.dto.request.UserCreateDto;
import io.project.easycut.easy_cut.domain.user.dto.response.UserResponse;

public interface UserService {

  UserResponse createUser(UserCreateDto userCreateDto);
}
