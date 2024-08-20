package com.steganography.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AwsS3Service {

    @Autowired
    private AmazonS3 s3Client;

    private String bucketName = "your-bucket-name";

    public String uploadFile(MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }

    public byte[] downloadFile(String filename) {
        try {
            return s3Client.getObject(bucketName, filename).getObjectContent().readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
