package io.project.easycut.easy_cut.global.exception;

import java.util.Map;
import lombok.Getter;

@Getter
public class EasyCutException extends RuntimeException {

  private final ErrorCode errorCode;
  private final Map<String, Object> details;

  /**
   * <p>상세 에러 정보가 포함되지 않는 경우</p>
   *
   * @param errorCode
   */
  public EasyCutException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.details = Map.of();
  }

  /**
   * <p>상세 에러 정보가 포함되어 있는 경우</p>
   *
   * @param errorCode
   * @param details 상세 에러 정보
   */
  public EasyCutException(ErrorCode errorCode, Map<String, Object> details) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.details = details;
  }
}
