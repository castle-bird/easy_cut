package io.project.easycut.easy_cut.domain.reservation.entity;

import io.project.easycut.easy_cut.domain.store.entity.Store;
import io.project.easycut.easy_cut.domain.store.entity.TimeSlot;
import io.project.easycut.easy_cut.domain.user.entity.User;
import io.project.easycut.easy_cut.global.entity.BaseUpdateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseUpdateTimeEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private ReservationStatus status = ReservationStatus.PENDING; // 예약 잡히는 순간 PENDING상태이므로 기본값 설정

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @JoinColumn(name = "store_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Store store;

  // unique = true를 통해 같은 시간 중복예약을 막음
  @JoinColumn(name = "time_slot_id", nullable = false, unique = true)
  @OneToOne(fetch = FetchType.LAZY)
  private TimeSlot timeSlot;

  @Builder
  public Reservation(User user, Store store, TimeSlot timeSlot) {
    this.user = user;
    this.store = store;
    this.timeSlot = timeSlot;
  }

  public void cancel() {
    this.status = ReservationStatus.CANCELED;
  }

  public void complete() {
    this.status = ReservationStatus.COMPLETED;
  }

  public void confirm() {
    this.status = ReservationStatus.CONFIRMED;
  }

  public void noShow() {
    this.status = ReservationStatus.NOSHOW;
  }
}
