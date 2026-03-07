package io.project.easycutback.domain.store.repository;

import io.project.easycutback.domain.store.entity.StoreImage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, UUID> {

}
