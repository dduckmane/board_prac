package com.project.board.domain.board.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardSaveForm {
    private MultipartFile thumbNail;
    private String title;
    private String representativeArea;
    private String detailArea;
    private List<String> tag;
    private int price;
    private String content;
    private List <MultipartFile> attachFiles;
}
