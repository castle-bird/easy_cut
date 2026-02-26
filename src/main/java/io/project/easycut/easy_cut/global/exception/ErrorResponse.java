package io.project.easycut.easy_cut.global.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
    LocalDateTime timestamp, // 발생 시각
    int status,              // HTTP 상태 코드
    String code,             // 커스텀 에러 코드
    String message,          // 에러 메시지
    String path,             // 요청 경로
    List<ErrorDetails> errorDetails  // 에러 상세내역
) {

  /**
   * (선택) 에러 상세내역
   */
  public record ErrorDetails(
      String field,
      String value,
      String reason
  ) {

  }

  /**
   * <p>상세정보 없는 생성자</p>
   *
   * @param code enum ErrorCode
   * @param path 에러 발생한 URI 경로
   * @return
   */
  public static ErrorResponse of(ErrorCode code, String path) {
    return new ErrorResponse(
        LocalDateTime.now(),
        code.getStatus().value(),
        code.getCode(),
        code.getMessage(),
        path,
        List.of()
    );
  }

  /**
   * <p>상세정보 있는 생성자</p>
   *
   * @param code   enum ErrorCode
   * @param path   에러 발생한 URI 경로
   * @param errors 에러 상세내역
   * @return
   */
  public static ErrorResponse of(ErrorCode code, String path, List<ErrorDetails> errors) {
    return new ErrorResponse(
        LocalDateTime.now(),
        code.getStatus().value(),
        code.getCode(),
        code.getMessage(),
        path,
        errors
    );
  }
}
