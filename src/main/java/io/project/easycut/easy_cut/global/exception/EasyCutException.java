package io.project.easycut.easy_cut.global.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class EasyCutException extends RuntimeException {

  private final ErrorCode errorCode;
  private final List<ErrorResponse.ErrorDetails> errorDetails;

  // 에러 디테일이 없는 생성자
  public EasyCutException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.errorDetails = new ArrayList<>();
  }

  // 에러 디테일이 있는 생성자
  public EasyCutException(ErrorCode errorCode, List<ErrorResponse.ErrorDetails> errorDetails) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
    this.errorDetails = errorDetails;
  }
}
