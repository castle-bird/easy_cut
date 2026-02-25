package io.project.easycut.easy_cut.domain.store.entity;

import io.project.easycut.easy_cut.global.entity.BaseUpdateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_slots")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeSlot extends BaseUpdateTimeEntity {

  /**
   * Batch를 이용해 매달 영업 가능한 날짜와 시간을 자동으로 정의할 것이므로
   * 다른 Entity들처럼 update를 만들지 않았음.
   * isAvailable를 통해 예약가능/불가능만 수정할 수 있도록 하였음.
   * 날짜/시간이 변경 가능하게 되면 데이터 무결성 침해.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Column(name = "available_date", nullable = false)
  private LocalDate availableDate;

  @Column(name = "available_time", nullable = false)
  private LocalTime availableTime;

  @Column(name = "is_available", nullable = false)
  private boolean isAvailable = true;

  @Builder
  public TimeSlot(Store store, LocalDate availableDate, LocalTime availableTime) {
    this.store = store;
    this.availableDate = availableDate;
    this.availableTime = availableTime;
  }

  public void reserve() {
    this.isAvailable = false;
  }

  public void release() {
    this.isAvailable = true;
  }
}
