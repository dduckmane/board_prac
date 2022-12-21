package com.project.board.domain.member.repository;

import com.project.board.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member>findByUsername(String username);
}
