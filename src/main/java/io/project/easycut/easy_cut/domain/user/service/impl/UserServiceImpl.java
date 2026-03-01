package io.project.easycut.easy_cut.domain.user.service.impl;

import io.project.easycut.easy_cut.domain.user.dto.request.UserCreateDto;
import io.project.easycut.easy_cut.domain.user.dto.response.UserResponse;
import io.project.easycut.easy_cut.domain.user.entity.User;
import io.project.easycut.easy_cut.domain.user.exception.UserException;
import io.project.easycut.easy_cut.domain.user.mapper.UserMapper;
import io.project.easycut.easy_cut.domain.user.repository.UserRepository;
import io.project.easycut.easy_cut.domain.user.service.UserService;
import io.project.easycut.easy_cut.global.exception.ErrorCode;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserResponse createUser(UserCreateDto userCreateDto) {
    // 이메일 중복 체크
    if (userRepository.existsByEmail(userCreateDto.email())) {
      throw new UserException(ErrorCode.DUPLICATE_EMAIL, Map.of("email", userCreateDto.email()));
    }

    // 전화번호 중복 체크
    if (userRepository.existsByPhone(userCreateDto.phone())) {
      throw new UserException(ErrorCode.DUPLICATE_PHONE, Map.of("phone", userCreateDto.phone()));
    }

    // DTO → Entity 변환
    User user = userMapper.toEntity(userCreateDto);

    String encodedPassword = passwordEncoder.encode(userCreateDto.password());
    user.changePassword(encodedPassword);

    // 저장 및 반환
    User savedUser = userRepository.save(user);

    return userMapper.toUserResponse(savedUser);
  }
}
