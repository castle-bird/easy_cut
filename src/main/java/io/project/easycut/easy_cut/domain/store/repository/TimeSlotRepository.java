package io.project.easycut.easy_cut.domain.store.repository;

import io.project.easycut.easy_cut.domain.store.entity.TimeSlot;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {

}
