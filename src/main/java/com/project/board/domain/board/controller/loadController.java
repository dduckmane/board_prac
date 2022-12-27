package com.project.board.domain.board.controller;


import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.global.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class loadController {

    private final BoardRepository boardRepository;


    @Value("${custom.upload.path}")
    private String UPLOAD_PATH;
    @ResponseBody
    @GetMapping(value ="/images", produces = MediaType.IMAGE_PNG_VALUE)
    public UrlResource downloadImage(@RequestParam Long itemId) throws
            MalformedURLException {

        Board board = boardRepository.findById(itemId).orElseThrow();
        String storeFileName = board.getThumbNail().getStoreFileName();

        String fileFullPath = FileUtils.fileFullPath(storeFileName, UPLOAD_PATH);

        return new UrlResource("file:" + fileFullPath);
    }
}
