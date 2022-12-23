package com.project.board;

import com.project.board.global.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@Slf4j
public class UploadApiController {
    @Value("${custom.ckeditor.path}")
    private String UPLOAD_PATH;

    @PostMapping("food/imageUpload.do")
    // 이미지 업로드
    public void imageUpload(HttpServletRequest request,
                            HttpServletResponse response, MultipartHttpServletRequest multiFile
            , @RequestParam MultipartFile upload) throws Exception{

        PrintWriter printWriter = null;

        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try{

            String storeFileName = FileUtils.uploadFile(upload, UPLOAD_PATH);

            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "/food/ckImgSubmit.do?storeFileName=" + storeFileName; // 작성화면

            // 업로드시 메시지 출력
            printWriter.println("{\"filename\" : \""+storeFileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush();

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if(printWriter != null) { printWriter.close(); }
        }
        return;
    }
    // 서버로 전송된 이미지 뿌려주기
    @RequestMapping(value="/food/ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value="storeFileName") String storeFileName
            , HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        System.out.println("ckSubmit시작");

        //서버에 저장된 이미지 경로
        String fileFullPath = FileUtils.fileFullPath(storeFileName, UPLOAD_PATH);
        System.out.println("fileFullPath = " + fileFullPath);
        File imgFile = new File(fileFullPath);

        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
        if(imgFile.isFile()){
            byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;

            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;

            try{
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();

                while((readByte = fileInputStream.read(buf)) != -1){
                    outputStream.write(buf, 0, readByte);
                }

                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf, 0, length);
                out.flush();

            }catch(IOException e){
                e.printStackTrace();
            }finally {
                outputStream.close();
                fileInputStream.close();
                out.close();
            }
        }
    }
}