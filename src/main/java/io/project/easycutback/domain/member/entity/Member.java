package io.project.easycutback.domain.member.entity;

import io.project.easycutback.domain.store.entity.Store;
import io.project.easycutback.global.entity.BaseUpdatableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseUpdatableEntity {

  @Column(name = "email", nullable = false, unique = true, length = 255)
  private String email;

  @Column(name = "password", nullable = false, length = 255)
  private String password;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "phone", nullable = false, length = 20)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false, length = 20)
  private MemberRole role;

  @Column(name = "is_deleted")
  private boolean isDeleted = false;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  /**
   * ================= 연관 관계 =================
   */

  @OneToOne(mappedBy = "owner")
  private Store store;

  @Builder
  public Member(
      String email,
      String password,
      String name,
      String phone,
      MemberRole role
  ) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phone = phone;
    this.role = role;
  }

  public void update(
      String email,
      String password,
      String name,
      String phone,
      MemberRole role
  ) {

    if (email != null && !email.isBlank()) {
      this.email = email;
    }

    if (password != null && !password.isBlank()) {
      this.password = password;
    }

    if (name != null && !name.isBlank()) {
      this.name = name;
    }

    if (phone != null && !phone.isBlank()) {
      this.phone = phone;
    }

    if (role != null) {
      this.role = role;
    }
  }

  /**
   * 누적데이터를 위해 Soft Delete함
   */
  public void delete() {
    this.isDeleted = true;
    this.deletedAt = LocalDateTime.now();
  }
}