package io.project.easycutback.domain.member.repository;

import io.project.easycutback.domain.member.entity.Member;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

}
