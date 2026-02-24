package io.project.easycut.easy_cut.global.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseIdEntity {

  // 모든 엔티티의 기본키(ID)를 공통 관리하기 위한 상속용 클래스
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
