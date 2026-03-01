package io.project.easycut.easy_cut.domain.user.exception;

import io.project.easycut.easy_cut.global.exception.ErrorCode;
import io.project.easycut.easy_cut.global.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorResponse> handleUserException(UserException e, HttpServletRequest request) {

    log.error("[UserException] - Path: {}, Message: {}", request.getRequestURI(), e.getMessage());

    ErrorCode errorCode = e.getErrorCode();

    // 에러 디테일이 없으면 디테일 없는 ErrorResponse 생성
    ErrorResponse errorResponse = e.getDetails().isEmpty()
        ? ErrorResponse.of(errorCode, request.getRequestURI())
        : ErrorResponse.of(errorCode, request.getRequestURI(), e.getDetails());

    return new ResponseEntity<>(errorResponse, errorCode.getStatus());
  }
}
