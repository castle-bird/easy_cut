package io.project.easycut.easy_cut.global.exception;

import java.util.Map;
import lombok.Getter;

@Getter
public class EasyCutException extends RuntimeException {

  private final ErrorCode errorCode;
  private final Map<String, Object> details;

  /**
   * 상세 에러 정보가 없는 경우
   */
  public EasyCutException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.details = Map.of();
  }

  /**
   * 상세 에러 정보를 포함하는 경우
   */
  public EasyCutException(ErrorCode errorCode, Map<String, Object> details) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.details = details;
  }
}
