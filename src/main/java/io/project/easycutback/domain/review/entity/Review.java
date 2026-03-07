package io.project.easycutback.domain.review.entity;

import io.project.easycutback.domain.reservation.entity.Reservation;
import io.project.easycutback.domain.member.entity.Member;
import io.project.easycutback.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

  @Column(name = "rating", nullable = false)
  private BigDecimal rating;

  @Column(name = "comment", columnDefinition = "TEXT")
  private String comment;

  /**
   * ================= 연관 관계 =================
   */

  @JoinColumn(name = "member_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  // unique = true로 한 예약당 하나의 리뷰만 작성할 수 있도록 DB 수준에서 중복을 방지함.
  @JoinColumn(name = "reservation_id", nullable = false, unique = true)
  @OneToOne(fetch = FetchType.LAZY)
  private Reservation reservation;

  @Builder
  public Review(
      BigDecimal rating,
      String comment,
      Member member,
      Reservation reservation
  ) {
    this.rating = rating;
    this.comment = comment;
    this.member = member;
    this.reservation = reservation;
  }

  public void update(
      BigDecimal rating,
      String comment
  ) {

    if (rating != null) {
      this.rating = rating;
    }

    if (comment != null && !comment.isBlank()) {
      this.comment = comment;
    }
  }
}
