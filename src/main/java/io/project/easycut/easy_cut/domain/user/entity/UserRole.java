package io.project.easycut.easy_cut.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
  CUSTOMER("일반 사용자"),
  OWNER("미용실 원장님"),
  ADMIN("페이지 관리자");

  private final String description;
}
