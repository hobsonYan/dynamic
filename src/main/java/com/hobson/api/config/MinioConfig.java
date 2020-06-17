package com.hobson.api.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yan hongbo
 * @date 2020/6/16 16:40
 * @description
 */
@Configuration
public class MinioConfig {

    @Value("${min.io.url}")
    private String url;

    @Value("${min.io.accessKey}")
    private String accessKey;

    @Value("${min.io.secretKey}")
    private String secretKey;

    @Value("${min.io.bucket}")
    private String bucket;

    @Bean(name = "minioClient")
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
        boolean isExist = minioClient.bucketExists(bucket);
        if (!isExist) {
            minioClient.makeBucket(bucket);
        }
        return minioClient;
    }
}
