package com.project.board.global.util;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileUtils {

    private static final Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<>();
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
    }

    public static String uploadFile(MultipartFile file, String uploadPath) {
        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        String newUploadPath = getNewUploadPath(uploadPath);

        File f = new File(newUploadPath, newFileName);

        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileFullPath = newUploadPath + File.separator + newFileName;
        String responseFilePath = fileFullPath.substring(uploadPath.length()+1);

        return responseFilePath.replace("\\", "/");
    }

    public static String fileFullPath(String storeFileName, String uploadPath) {

        String fileFullPath = uploadPath + File.separator + storeFileName;

        return fileFullPath.replace("\\", "/");
    }

    private static String getNewUploadPath(String uploadPath) {

        LocalDateTime now = LocalDateTime.now();
        int y = now.getYear();
        int m = now.getMonthValue();
        int d = now.getDayOfMonth();

        String[] dateInfo = {
                String.valueOf(y)
                , len2(m)
                , len2(d)
        };

        String newUploadPath = uploadPath;

        for (String date : dateInfo) {
            newUploadPath += File.separator + date;

            File dirName = new File(newUploadPath);
            if (!dirName.exists()) dirName.mkdir();
        }

        return newUploadPath;
    }

    private static String len2(int n) {
        return new DecimalFormat("00").format(n);
    }

}
