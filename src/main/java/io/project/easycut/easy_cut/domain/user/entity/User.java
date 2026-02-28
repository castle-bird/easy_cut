package io.project.easycut.easy_cut.domain.user.entity;

import io.project.easycut.easy_cut.domain.store.entity.Store;
import io.project.easycut.easy_cut.global.entity.BaseUpdateEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseUpdateEntity {

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, length = 20)
  private String phone;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private UserRole role;

  @OneToOne(mappedBy = "owner")
  private Store store;


  @Builder
  public User(String email, String password, String name, String phone, UserRole role) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phone = phone;
    this.role = role != null ? role : UserRole.CUSTOMER;
  }

  public void update(String email, String password, String name, String phone, UserRole role) {

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
}