package com.skwzz.toyspringboot.s3.controller;

import com.skwzz.toyspringboot.s3.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/s3")
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @PostMapping("/file")
    public ResponseEntity<List<String>> s3upload(@RequestPart(value="filename", required = false) List<MultipartFile> multipartFiles){
        log.info("MultipartFile List size : "+String.valueOf(multipartFiles.size()));
        List<String> uploadResult = awsS3Service.uploadFile(multipartFiles);
        return new ResponseEntity<>(uploadResult, HttpStatus.OK);
    }
}
