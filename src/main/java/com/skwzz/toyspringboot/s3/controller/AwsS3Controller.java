package com.skwzz.toyspringboot.s3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/s3")
public class AwsS3Controller {

    @PostMapping("/image")
    public ResponseEntity s3upload(MultipartFile multipartFile){
        log.info(multipartFile.toString());
        return new ResponseEntity(HttpStatus.OK);
    }
}
