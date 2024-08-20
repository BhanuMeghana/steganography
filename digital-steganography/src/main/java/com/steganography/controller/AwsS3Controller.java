package com.steganography.controller;

import com.steganography.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class AwsS3Controller {

    @Autowired
    private AwsS3Service awsS3Service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return awsS3Service.uploadFile(file);
    }

    @GetMapping("/download/{filename}")
    public byte[] downloadFile(@PathVariable String filename) {
        return awsS3Service.downloadFile(filename);
    }
}
