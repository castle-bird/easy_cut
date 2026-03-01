package io.project.easycut.easy_cut.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  // 모든 엔티티의 기본키(ID)를 공통 관리하기 위한 상속용 클래스
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  // 등록일(createdAt)만 필요한 엔티티(예: 미용실 이미지, 리뷰 등)를 위한 기반 클래스
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

}
