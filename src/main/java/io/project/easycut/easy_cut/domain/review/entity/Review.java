package io.project.easycut.easy_cut.domain.review.entity;

import io.project.easycut.easy_cut.domain.reservation.entity.Reservation;
import io.project.easycut.easy_cut.domain.user.entity.User;
import io.project.easycut.easy_cut.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

  @Column(name = "rating", nullable = false)
  private Double rating;

  @Column(name = "comment", columnDefinition = "TEXT")
  private String comment;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  // unique = true로 한 예약당 하나의 리뷰만 작성할 수 있도록 DB 수준에서 중복을 방지함.
  @JoinColumn(name = "reservation_id", nullable = false, unique = true)
  @OneToOne(fetch = FetchType.LAZY)
  private Reservation reservation;

  @Builder
  public Review(Double rating, String comment, User user, Reservation reservation) {
    this.user = user;
    this.reservation = reservation;
    this.rating = rating;
    this.comment = comment;
  }

  public void update(Double rating, String comment) {
    if (rating != null) {
      this.rating = rating;
    }

    if (comment != null && !comment.isBlank()) {
      this.comment = comment;
    }
  }
}
