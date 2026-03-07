package io.project.easycut.easy_cut.domain.store.repository;

import io.project.easycut.easy_cut.domain.store.entity.StoreImage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, UUID> {

}
