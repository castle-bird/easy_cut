package io.project.easycut.easy_cut.domain.reservation.entity;

import io.project.easycut.easy_cut.global.entity.BaseUpdateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseUpdateEntity {

}
