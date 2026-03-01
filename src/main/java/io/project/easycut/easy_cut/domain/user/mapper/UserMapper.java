package io.project.easycut.easy_cut.domain.user.mapper;

import io.project.easycut.easy_cut.domain.user.dto.request.UserCreateDto;
import io.project.easycut.easy_cut.domain.user.dto.response.UserResponse;
import io.project.easycut.easy_cut.domain.user.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

  UserResponse toUserResponse(User user);

  // 추후 값을 넣어주기 위해 이름, 핸드폰 번호만 변환
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "store", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  User toEntity(UserCreateDto userCreateDto);
}
