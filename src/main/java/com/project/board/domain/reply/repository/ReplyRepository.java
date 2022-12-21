package com.project.board.domain.reply.repository;

import com.project.board.domain.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long>,ReplyRepositoryCustom{

}
