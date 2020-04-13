package com.panshi.download;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @ClassName DownloadController
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  18:14
 * @Version
 */
@Controller
public class DownloadController {
    @RequestMapping("/itemList")
    public String itemList() {
        return "itemList";
    }

    @RequestMapping("/download")
    @ResponseBody
    public String download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("d://a//c//" + fileName);
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
//            log.error("{}",e);
            return "error";
        }
        return "success";
    }

}
