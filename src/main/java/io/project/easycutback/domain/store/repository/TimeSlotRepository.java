package io.project.easycutback.domain.store.repository;

import io.project.easycutback.domain.store.entity.TimeSlot;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {

}
