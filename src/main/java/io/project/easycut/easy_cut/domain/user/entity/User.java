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

  @Column(nullable = false, unique = true, length = 255, updatable = false)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false, unique = true, length = 20)
  private String phone;

  // 기본값 일반 사용자
  // 권한은 가입 후 변경할 수 있도록
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private UserRole role = UserRole.CUSTOMER;

  @OneToOne(mappedBy = "owner")
  private Store store;


  @Builder
  public User(String email, String password, String name, String phone) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.phone = phone;
  }

  /**
   * 비밀번호 변경 - 암호화 후에 넣을 것.
   */
  public void changePassword(String encodedPassword) {
    this.password = encodedPassword;
  }

  /**
   * 권한 변경
   */
  public void assignRole(UserRole newRole) {
    this.role = newRole;
  }

  /**
   * 이름 변경
   */
  public void changeName(String newName) {
    this.name = newName;
  }

  /**
   * 휴대폰 변경
   */
  public void changePhone(String newPhone) {
    this.phone = newPhone;
  }
}
