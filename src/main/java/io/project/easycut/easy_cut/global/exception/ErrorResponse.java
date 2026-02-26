package io.project.easycut.easy_cut.global.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
    LocalDateTime timestamp, // 발생 시각
    int status,              // HTTP 상태 코드
    String code,             // 커스텀 에러 코드
    String message,          // 에러 메시지
    String path,             // 요청 경로
    Map<String, Object> details // 에러 상세내역
) {

  /**
   * 상세정보 없는 생성자
   */
  public static ErrorResponse of(ErrorCode code, String path) {
    return new ErrorResponse(
        LocalDateTime.now(),
        code.getStatus().value(),
        code.getCode(),
        code.getMessage(),
        path,
        Map.of()
    );
  }

  /**
   * 상세정보 있는 생성자
   */
  public static ErrorResponse of(ErrorCode code, String path, Map<String, Object> details) {
    return new ErrorResponse(
        LocalDateTime.now(),
        code.getStatus().value(),
        code.getCode(),
        code.getMessage(),
        path,
        details
    );
  }
}
