package com.project.board.domain.board.controller.init;

import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Tag;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.project.board.domain.board.domain.boardenum.Category.*;
import static com.project.board.domain.board.domain.boardenum.Category.JAPAN;
import static com.project.board.domain.board.domain.boardenum.Tag.*;
import static com.project.board.domain.board.domain.boardenum.Tag.PLAY;

@Component
@Getter
public class BoardInit {
    private List<Tag> tags=new ArrayList<>();
    private List<Category> categories=new ArrayList<>();

    @PostConstruct
    public void init(){
        initCategory();
        initTags();
    }
    public void initCategory(){
        categories.add(KOREAN);
        categories.add(AMERICA);
        categories.add(CHINA);
        categories.add(JAPAN);

    }
    public void initTags(){
        tags.add(PRICE);
        tags.add(RESERVATION);
        tags.add(MOOD);
        tags.add(PLAY);

    }



}
