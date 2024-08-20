package com.steganography;

import static org.mockito.Mockito.*;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;

@SpringBootTest
@SpringJUnitConfig
public class AwsS3ServiceTest {

    @Mock
    private AmazonS3 amazonS3;

    @InjectMocks
    private AwsS3Service awsS3Service;

    public AwsS3ServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadFile() {
        File file = new File("test.png");
        when(amazonS3.putObject(anyString(), anyString(), any(File.class)))
                .thenReturn(new PutObjectResult());

        awsS3Service.uploadFile("my-bucket", "test.png", file);

        verify(amazonS3).putObject("my-bucket", "test.png", file);
    }

    @Test
    public void testDownloadFile() {
        S3Object s3Object = new S3Object();
        when(amazonS3.getObject(anyString(), anyString())).thenReturn(s3Object);

        S3Object result = awsS3Service.downloadFile("my-bucket", "test.png");

        verify(amazonS3).getObject("my-bucket", "test.png");
        assertNotNull(result);
    }
}
