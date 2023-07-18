package com.crs.common.util;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author LZ
 * @date 2022/9/3 15:55
 */
@Component
@RequiredArgsConstructor
public class MinioUtil {

    private final MinioClient minioClient;

    /**
     * 查询桶名是否存在
     * @param bucketName 桶名
     * @return 是否存在
     */
    @SneakyThrows
    public R<Boolean> isBucketExist(String bucketName){
        return R.ok(minioClient.bucketExists(
                 BucketExistsArgs.builder()
                .bucket(bucketName)
                .build()));
    }

    /**
     * 创建桶
     * @param bucketName 桶名
     */
    @SneakyThrows
    public R<Boolean> createBucket(String bucketName){
        minioClient.makeBucket(
                 MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
        return R.ok(Boolean.TRUE);
    }

    /**
     * 下载文件
     * @param bucketName 桶名
     * @param fileName 文件名
     * @param downloadPath 文件存储路径
     */
    @SneakyThrows
    public R<Boolean> getFile(String bucketName, String fileName, String downloadPath){
        GetObjectResponse file = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        File path = new File(downloadPath);
        FileUtils.copyInputStreamToFile(file,path);
        return R.ok(Boolean.TRUE);
    }

    /**
     * 上传文件
     * @param file 文件
     * @param bucketName 桶名
     * @return
     */
    @SneakyThrows
    public R<Boolean> uploadFile(MultipartFile file,String bucketName){
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName)
                .object("fileName")
                .stream(file.getInputStream(),file.getSize(),-1)
                .contentType(file.getContentType())
                .build());
        return R.ok(Boolean.TRUE);
    }

    /**
     * 下载文件
     * @param bucketName 桶名
     * @param fileName 文件名
     * @param downloadPath 文件下载包含文件名
     */
    @SneakyThrows
    public R<Boolean> downloadFile(String bucketName,String fileName,String downloadPath){
        minioClient.downloadObject(
                DownloadObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .filename(downloadPath).build());
        return R.ok(Boolean.TRUE);
    }

    /**
     * 文件临时访问路径,有效期七天
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return 文件访问路径
     */
    @SneakyThrows
    public R<String> getFilePath(String bucketName,String fileName){
      return R.ok(minioClient.getPresignedObjectUrl(
              GetPresignedObjectUrlArgs.builder()
              .bucket(bucketName)
              .object(fileName)
              .method(Method.GET)
              .build()));
    }

    /**
     * 删除文件
     * @param bucketName 桶名
     * @param fileName 文件名
     * @return
     */
    @SneakyThrows
    public R<Boolean> deleteFile(String bucketName,String fileName){
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
        return R.ok(Boolean.TRUE);
    }

    /**
     * 删除桶
     * @param bucketName 桶名
     */
    @SneakyThrows
    public R<Boolean> deleteBucket(String bucketName){
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        return R.ok(Boolean.TRUE);
    }
}
