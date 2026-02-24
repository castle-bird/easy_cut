package io.project.easycut.easy_cut.domain.review.entity;

import io.project.easycut.easy_cut.global.entity.BaseCreateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseCreateEntity {
}
