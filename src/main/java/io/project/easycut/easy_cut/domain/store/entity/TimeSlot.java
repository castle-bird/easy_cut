package io.project.easycut.easy_cut.domain.store.entity;

import io.project.easycut.easy_cut.global.entity.BaseUpdateTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_slots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeSlot extends BaseUpdateTimeEntity {
}
