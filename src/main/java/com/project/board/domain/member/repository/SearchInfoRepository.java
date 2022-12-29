package com.project.board.domain.member.repository;


import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchInfoRepository extends JpaRepository<SearchInfo,Long> {

    @EntityGraph(attributePaths = {"member"})
    Optional<SearchInfo> findByMember(Member member);

    Optional<SearchInfo> findSearchInfoByMember(Member member);


}
