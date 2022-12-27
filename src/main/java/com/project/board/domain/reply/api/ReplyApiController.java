package com.project.board.domain.reply.api;

import com.project.board.domain.reply.dto.response.ListDto;
import com.project.board.domain.reply.dto.response.ReplyDto;
import com.project.board.domain.reply.dto.request.ReplySaveDto;
import com.project.board.domain.reply.dto.request.ReplyUpdateDto;
import com.project.board.domain.reply.repository.ReplyRepository;
import com.project.board.domain.reply.service.ReplyService;
import com.project.board.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reply")
public class ReplyApiController {
    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    @GetMapping("/list/{boardId}")
    public ListDto list(
            @PathVariable Long boardId
            , @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<ReplyDto> results = replyRepository
                .searchAll(boardId, pageable)
                .map(ReplyDto::new);

        return ListDto.builder()
                .nowPage(results.getPageable().getPageNumber() + 1)
                .endPage(Math.min(results.getPageable().getPageNumber() + 1 + 5, results.getTotalPages()))
                .startPage(Math.max(results.getPageable().getPageNumber() + 1 - 4, 1))
                .results(results)
                .build();
    }
    @PostMapping("")
    public ResponseEntity<String> save(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @RequestBody ReplySaveDto replySaveDto
    ){
        replyService.save(
                replySaveDto.getBoardNo()
                , principalDetails.getMember()
                , replySaveDto.getReplyText()
        );

        return new ResponseEntity<>("save", HttpStatus.CREATED);
    }
    @PutMapping("/{replyId}")
    public ResponseEntity<String> update(
            @PathVariable Long replyId
            , @RequestBody ReplyUpdateDto replyUpdateDto
    ){
        replyService.update(replyId, replyUpdateDto.getReplyText());

        return new ResponseEntity<>("update",HttpStatus.OK);
    }
    @DeleteMapping("/{replyId}")
    public ResponseEntity<String> delete(@PathVariable Long replyId){
        replyService.delete(replyId);

        return new ResponseEntity<>("delete",HttpStatus.OK);
    }

}
