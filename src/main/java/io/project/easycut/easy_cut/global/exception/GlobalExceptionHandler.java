package io.project.easycut.easy_cut.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  /**
   * [EasyCutException] - 커스텀 예외처리 (도메인별 상속 예외 포함)
   */
  @ExceptionHandler(EasyCutException.class)
  public ResponseEntity<ErrorResponse> handleEasyCutException(EasyCutException e, HttpServletRequest request) {
    log.error("[EasyCutException] - Path: {}, Message: {}", request.getRequestURI(), e.getMessage());

    ErrorCode errorCode = e.getErrorCode();

    // 에러 디테일이 없으면 디테일 없는 ErrorResponse 생성
    ErrorResponse errorResponse = e.getErrorDetails().isEmpty()
        ? ErrorResponse.of(errorCode, request.getRequestURI())
        : ErrorResponse.of(errorCode, request.getRequestURI(), e.getErrorDetails());

    return new ResponseEntity<>(errorResponse, errorCode.getStatus());
  }

  /**
   * [GlobalException] - 기본 예외처리
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
    log.error("[GlobalException] - Path: {}", request.getRequestURI(), e);

    ErrorResponse errorResponse = ErrorResponse.of(
        ErrorCode.INTERNAL_SERVER_ERROR,
        request.getRequestURI()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
