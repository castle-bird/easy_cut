package io.project.easycut.easy_cut.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity extends BaseIdEntity {

  // 등록일(createdAt)만 필요한 엔티티(예: 미용실 이미지, 리뷰 등)를 위한 기반 클래스
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

}
