package com.hobson.api.controller;

import com.hobson.api.util.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yan hongbo
 * @date 2020/6/17 10:34
 * @description
 */
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    private MinioUtil minioUtil;

    @RequestMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file")MultipartFile file) {
        String name = minioUtil.uploadFile(file);
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam("fileName") String fileName) {
        return minioUtil.delete(fileName);
    }

    @RequestMapping("/getUrl")
    public String getUrl(@RequestParam("fileName") String fileName) {
        return minioUtil.getFileUrl(fileName);
    }

    @RequestMapping("/down")
    public void down(@RequestParam("filename") String filename, HttpServletResponse response) {
       try {
           InputStream inputStream = minioUtil.getFile(filename);
           byte[] b = new byte[1024];
           int length = 0;
           response.reset();

           response.setHeader("Content-Disposition", "attachment;filename=" +
                   URLEncoder.encode(filename, "utf-8"));
           response.setContentType("application/octet-stream");
           response.setCharacterEncoding("utf-8");
           OutputStream outputStream = response.getOutputStream();
           while ((length = inputStream.read(b)) > 0) {
               outputStream.write(b, 0, length);
           }
           outputStream.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
