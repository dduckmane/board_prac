package com.project.board.domain.board.dto.request;

import com.project.board.domain.board.domain.BoardFiles;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardFilesDto {
    private Long boardFilesId;
    private String uploadFileName;

    public BoardFilesDto(BoardFiles boardFiles) {
        this.boardFilesId = boardFiles.getId();
        this.uploadFileName = boardFiles.getAttachFiles().getUploadFileName();
    }
}
