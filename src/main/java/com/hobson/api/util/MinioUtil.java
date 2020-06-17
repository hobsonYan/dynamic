package com.hobson.api.util;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author yan hongbo
 * @date 2020/6/16 16:24
 * @description
 */
@Component
@Slf4j
public class MinioUtil {

    @Value("${min.io.bucket}")
    private String bucket;

    private final MinioClient minioClient;

    @Autowired
    public MinioUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadFile(MultipartFile file) {
        try(ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes())) {
            return uploadFile(bais, file.getOriginalFilename(), bucket);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String uploadFile(MultipartFile file, String myBucket) throws Exception {
        if (!minioClient.bucketExists(myBucket)) {
            minioClient.makeBucket(myBucket);
        }
        try(ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes())) {
            return uploadFile(bais, file.getOriginalFilename(), myBucket);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String uploadFile(InputStream inputStream, String filename, String myBucket) {

        StringBuilder objectName = new StringBuilder(filename);
        try {
            minioClient.putObject(myBucket, objectName.toString(), inputStream, String.valueOf(new HashMap(16)));
            return objectName.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public InputStream getFile(String filename) {
        return getFile(filename, bucket);
    }

    public InputStream getFile(String filename, String myBucket) {
        try {
            // 文件是否存在
            minioClient.statObject(myBucket, filename);
            // 获取文件
            return minioClient.getObject(myBucket, filename);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void lookUpLoadFile(HttpServletRequest request, HttpServletResponse response, String fid, String filename) {
        try (InputStream in = new MinioUtil(minioClient).getFile(fid);
             OutputStream output = response.getOutputStream()) {
            // 得到输入流
            if (in == null) {
                try (PrintWriter printWriter = response.getWriter()) {
                    printWriter.append("404 - File Not Exist");
                } catch (IOException e) {
                    log.error("数据异常: {}", e);
                }
                return;
            }
            response.reset();
            // res.setContentType(getMimeType(fileName));
            // https://gitee.com/dolyw/codes/2h1r6avwxumegjs89ztyn86
            response.addHeader("content-Disposition", "inline;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            byte[] b = new byte[4096];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                output.write(b, 0, i);
            }
        } catch (MalformedURLException me) {
            log.error("数据异常: {}", me);
        } catch (IOException e) {
            log.error("数据异常: {}", e);
        }
    }

    public String getFileUrl(String objectName) {
        return getFileUrl(objectName, 60 * 60 * 24);
    }

    public String getFileUrl(String objectName, Integer expires) {
        try {
            return minioClient.presignedGetObject(bucket, objectName, expires);
        } catch (Exception e) {
            log.error("{}文件获取失败", objectName);
            return "";
        }
    }

    public String getFileUrl(String objectName, String myBucket) {
        return getFileUrl(objectName, myBucket, 60 * 60 * 24);
    }

    public String getFileUrl(String objectName, String myBucket, Integer expires) {
        try {
            if (minioClient.bucketExists(myBucket)) {
                return minioClient.presignedGetObject(myBucket, objectName, expires);
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean delete(String filePath) {
        try {
            minioClient.removeObject(bucket, filePath);
            return true;
        } catch (Exception e) {
            log.error("删除失败，{}", e.getMessage());
        }
        return false;
    }

    public boolean delete(String filePath, String myBucket) {
        try {
            minioClient.removeObject(myBucket, filePath);
            return true;
        } catch (Exception e) {
            log.error("删除失败，{}", e.getMessage());
        }
        return false;
    }

}
