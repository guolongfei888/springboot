package com.panshi.upload;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName UploadDemo
 * @Description
 * @Author guolongfei
 * @Date 2020/4/13  15:51
 * @Version
 */
@Controller
public class UploadController {
    @RequestMapping("/upload")
//    @ResponseBody
    public String getUpload() {
        return "upload";
    }

    /* 方法一:

     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成MultipartFile 对象
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            //用来检测程序的运行时间
            long startTime = System.currentTimeMillis();
            System.out.println("fileName: "+file.getOriginalFilename());
            String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File filePath = new File("d:\\a\\"+file.getOriginalFilename());
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();//如果目录不存在,创建目录
            }

            try {
                // 获取输入流
                OutputStream os = new FileOutputStream(filePath);

//                BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));

                //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
                InputStream is = file.getInputStream();

                int len;
                byte[] bys = new byte[2048];
                while ((len=is.read(bys))!=-1) {
                    os.write(bys,0,len);
                    os.flush();
                }
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
            long endTime = System.currentTimeMillis();
            System.out.println("方法一的运行时间: " + String.valueOf(endTime - startTime) + "ms");
            return "success"; // 返回成功页面
        } else {
            return "error"; // 返回失败页面
        }
    }

    /*方法二:
     *
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload2(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "error";
        }
        long startTime = System.currentTimeMillis();
        System.out.println("fileName: " + file.getOriginalFilename());
        File  filePath = new File("D:\\a\\b\\" + System.currentTimeMillis() + file.getOriginalFilename());
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        return "success";
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @RequestMapping("/multiUpload")
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "d:\\a\\c\\";
        for (int i = 0;i<files.size();i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第"+(i++)+"个文件失败";
            }
            String fileName = file.getOriginalFilename();
            File dest = new File(filePath+fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);

            } catch (IOException e) {
                e.printStackTrace();
                return "上传第" + (i++) + "个文件失败";
            }
        }
        return "success";
    }

    /* 方法三:
     *  该方法 可以上传多个文件
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("/multiUpload2")
    public String multiUpload2(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        int count = 0;
        if (multipartResolver.isMultipart(request)) {

            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
                String basePath = "D:\\a\\";
                for (MultipartFile multipartFile : multipartFiles) {
                    String fileName = multipartFile.getOriginalFilename();
                    if (!StringUtils.isEmpty(fileName)) {
                        File filePath = new File(basePath + fileName);
                        if (!filePath.getParentFile().exists()) {
                            filePath.getParentFile().mkdirs();
                        }

                        try {
                            multipartFile.transferTo(filePath);
                            count++;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return "error";
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        if (count == 0) {
            return "error";
        } else {
            return "success";
        }
    }
}
