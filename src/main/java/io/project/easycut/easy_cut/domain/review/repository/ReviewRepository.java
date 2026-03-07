package io.project.easycut.easy_cut.domain.review.repository;

import io.project.easycut.easy_cut.domain.reservation.entity.Reservation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reservation, UUID> {

}
