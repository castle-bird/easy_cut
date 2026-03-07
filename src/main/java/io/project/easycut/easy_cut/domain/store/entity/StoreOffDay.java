package io.project.easycut.easy_cut.domain.store.entity;

import io.project.easycut.easy_cut.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_off_days")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreOffDay extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "off_day", nullable = false)
  private OffDay offDay;

  /**
   * ================= 연관 관계 =================
   */

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Builder
  public StoreOffDay(
      Store store,
      OffDay offDay
  ) {
    this.store = store;
    this.offDay = offDay;
  }
}
