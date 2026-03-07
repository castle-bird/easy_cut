package io.project.easycut.easy_cut.domain.member.repository;

import io.project.easycut.easy_cut.domain.member.entity.Member;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

}
