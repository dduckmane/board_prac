package com.project.board.domain.board;


import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.BoardFiles;
import com.project.board.domain.board.repository.BoardFilesRepository;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.global.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j
@RequiredArgsConstructor
public class loadController {

    private final BoardRepository boardRepository;

    private final BoardFilesRepository boardFilesRepository;

    private static final String UPLOAD_PATH = "C:\\sl_dev\\upload";
    @ResponseBody
    @GetMapping(value ="/images", produces = MediaType.IMAGE_PNG_VALUE)
    public UrlResource downloadImage(@RequestParam Long itemId) throws
            MalformedURLException {
        log.info("-------------downloadImage 실행----------- {}");

        Board board = boardRepository.findById(itemId).orElseThrow();

        String storeFileName = board.getThumbNail().getStoreFileName();

        String fileFullPath = FileUtils.fileFullPath(storeFileName, UPLOAD_PATH);
        System.out.println("fileFullPath = " + fileFullPath);
        return new UrlResource("file:" + fileFullPath);
    }
    @GetMapping("/attach")
    public ResponseEntity<UrlResource>downloadAttach(@RequestParam Long boardFilesId) throws MalformedURLException {

        BoardFiles boardFiles = boardFilesRepository.findById(boardFilesId).orElseThrow();

        String fileFullPath = FileUtils.fileFullPath(boardFiles.getAttachFiles().getStoreFileName(), UPLOAD_PATH);

        UrlResource urlResource = new UrlResource("file:" + fileFullPath);

        log.info("uploadFileName={}", boardFiles.getAttachFiles().getUploadFileName());

        String encodedUploadFileName = UriUtils.encode(boardFiles.getAttachFiles().getUploadFileName(), StandardCharsets.UTF_8);

        String contentDisposition="attachment; filename=\"" +
                encodedUploadFileName+"\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(urlResource);

    }
}
