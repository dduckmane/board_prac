package com.project.board.domain.member.repository;


import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchInfoRepository extends JpaRepository<SearchInfo,Long> {

    Optional<SearchInfo> findByMember(Member member);

}
