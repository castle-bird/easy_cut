package io.project.easycut.easy_cut.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@MappedSuperclass
public abstract class BaseUpdateTimeEntity extends BaseTimeEntity {

  // 등록일과 수정일(updatedAt)이 모두 필요한 일반적인 비즈니스 엔티티를 위한 기반 클래스
  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime updatedAt;

}
