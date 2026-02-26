package io.project.easycut.easy_cut.domain.store.entity;

import io.project.easycut.easy_cut.domain.user.entity.User;
import io.project.easycut.easy_cut.global.entity.BaseUpdateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stores")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseUpdateTimeEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String location;

  @Column(name = "open_time", nullable = false)
  private LocalTime openTime;

  @Column(name = "close_time", nullable = false)
  private LocalTime closeTime;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted = false;

  @OneToMany(mappedBy = "store")
  private List<StoreImage> images = new ArrayList<>();

  @Builder
  public Store(User owner, String name, String description, String location, LocalTime openTime, LocalTime closeTime) {
    this.owner = owner;
    this.name = name;
    this.description = description;
    this.location = location;
    this.openTime = openTime;
    this.closeTime = closeTime;
    this.isDeleted = false;
  }

  public void update(String name, String description, String location, LocalTime openTime, LocalTime closeTime) {
    if (name != null && !name.isBlank()) {
      this.name = name;
    }
    if (description != null && !description.isBlank()) {
      this.description = description;
    }
    if (location != null && !location.isBlank()) {
      this.location = location;
    }
    if (openTime != null) {
      this.openTime = openTime;
    }
    if (closeTime != null) {
      this.closeTime = closeTime;
    }
  }

  /**
   * 매장 삭제시 연결된 리뷰, 예약 목록 등
   * 누적데이터에 침해가 가기때문에 Soft Delete함
   */
  public void delete() {
    this.isDeleted = true;
  }
}
