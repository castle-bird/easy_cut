package io.project.easycut.easy_cut.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // 공통 에러 - Common
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다."),
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "허용되지 않은 HTTP 메서드입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 내부 오류가 발생했습니다."),
  ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "C004", "엔티티를 찾을 수 없습니다."),

  // User 에러
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "존재하지 않는 사용자입니다."),
  DUPLICATE_EMAIL(HttpStatus.CONFLICT, "U002", "이미 존재하는 이메일입니다."),
  DUPLICATE_PHONE(HttpStatus.CONFLICT, "U003", "이미 등록된 전화번호입니다."),
  INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U004", "비밀번호가 일치하지 않습니다."),
  ACCESS_DENIED(HttpStatus.FORBIDDEN, "U005", "접근 권한이 없습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;
}
