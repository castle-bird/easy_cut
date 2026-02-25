package io.project.easycut.easy_cut.domain.store.entity;

import io.project.easycut.easy_cut.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreImage extends BaseTimeEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @Column(nullable = false, length = 255)
  private String path;

  @Column(name = "content_type", nullable = false, length = 20)
  private String contentType;

  @Column(name = "original_name", nullable = false, length = 255)
  private String originalName;

  @Column(name = "is_main", nullable = false)
  private boolean isMain = false;

  @Builder
  public StoreImage(Store store, String path, String contentType, String originalName) {
    this.store = store;
    this.path = path;
    this.contentType = contentType;
    this.originalName = originalName;
  }

  public void setAsMain() {
    this.isMain = true;
  }

  public void setAsNotMain() {
    this.isMain = false;
  }
}
