package com.sight.domain.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sight.domain.image.error.ImageErrorCode;
import com.sight.domain.image.presentation.dto.res.ImageInfo;
import com.sight.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageInfo uploadImage(MultipartFile file) {
        try {

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String fileUrl = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

            return ImageInfo.from(fileUrl, fileName);

        } catch (IOException e) {
            throw new CustomException(ImageErrorCode.UPLOAD_FAIL);
        }
    }

    public void deleteImage(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        amazonS3Client.deleteObject(bucket, fileName);
    }
}
