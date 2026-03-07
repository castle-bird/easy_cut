package io.project.easycut.easy_cut.domain.store.repository;

import io.project.easycut.easy_cut.domain.store.entity.Store;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

}
