package io.project.easycut.easy_cut.domain.user.exception;

import io.project.easycut.easy_cut.global.exception.EasyCutException;
import io.project.easycut.easy_cut.global.exception.ErrorCode;
import java.util.Map;
import lombok.Getter;

@Getter
public class UserException extends EasyCutException {


  public UserException(ErrorCode errorCode) {
    super(errorCode);
  }

  public UserException(ErrorCode errorCode, Map<String, Object> details) {
    super(errorCode, details);
  }
}
