package io.project.easycut.easy_cut.domain.reservation.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationStatus {
  PENDING("예약 대기"),
  CONFIRMED("예약 확정"),
  COMPLETED("이용 완료"),
  CANCELED("취소됨"),
  NOSHOW("노쇼");

  private final String description;
}