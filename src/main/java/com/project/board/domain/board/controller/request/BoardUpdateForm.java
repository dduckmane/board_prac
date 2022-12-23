package com.project.board.domain.board.controller.request;

import com.project.board.domain.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardUpdateForm {
    private Long id;
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

    public BoardUpdateForm(Board board) {
        this.id=board.getId();
        //thumbNail 은 안줘도 된다. 새로 주면 바꾸고 안 주면 안바꾸면 된다.
        this.title = board.getTitle();
        this.representativeArea=board.getAddress().getRepresentativeArea();
        this.detailArea=board.getAddress().getDetailArea();
        this.tag=board.getTag();
        this.price=board.getPrice();
        this.content = board.getContent();

    }
}
