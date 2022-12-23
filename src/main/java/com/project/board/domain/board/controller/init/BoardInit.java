package com.project.board.domain.board.controller.init;

import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
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
    private List<Regions> regions =new ArrayList<>();


    @PostConstruct
    public void init(){
        initCategory();
        initTags();
        initRegions();
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
    public void initRegions(){
        regions.add(Regions.SEOUL);
        regions.add(Regions.GYEONGGI);
        regions.add(Regions.INCHEON);
        regions.add(Regions.GANG);
        regions.add(Regions.JS);
        regions.add(Regions.JN);
        regions.add(Regions.GS);
        regions.add(Regions.GN);
        regions.add(Regions.JEJU);
    }



}
