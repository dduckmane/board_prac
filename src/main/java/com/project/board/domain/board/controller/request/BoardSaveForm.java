package com.project.board.domain.board.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardSaveForm {
    private MultipartFile thumbNail;
    @Length(min = 2, max = 50)
    private String title;
    @Length( max = 50)
    @NotEmpty
    private String representativeArea;
    @Length( max = 50)
    @NotEmpty
    private String detailArea;
    @NotNull
    @Size(min = 1)
    private List<String> tag;
    @NotNull
    private int price;
    private String content;
}
