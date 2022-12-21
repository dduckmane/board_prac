package com.project.board.domain.board.dto.request;

import com.project.board.domain.board.domain.Board;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDetailsDto {
    private Long id;
    private String title;
    private String content;
    private Long viewCnt;
    private String username;
    private String createTime;
    private String detailArea;
    private List<BoardFilesDto> boardFilesDto;
    private int price;
    private List<String> tag;

    public BoardDetailsDto(Board board) {
        this.id=board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCnt = board.getViewCnt();
        this.username = board.getMember().getUsername();
        this.createTime = board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.detailArea = board.getAddress().getDetailArea();
        this.boardFilesDto = board.getAttachFiles().stream().map(BoardFilesDto::new).collect(Collectors.toList());
        this.price=board.getPrice();
        this.tag=board.getTag();

    }
}
