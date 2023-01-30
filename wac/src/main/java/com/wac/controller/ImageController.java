package com.wac.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wac.domain.Images;
import com.wac.service.ImagesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    
    private final ImagesService imagesService;

    /**
     * fid에 해당하는 이미지를 찾는 메서드
     * 
     * @param Integer fid
     * @return ajax 방식, fid에 해당하는 MultipartFile을 리턴
     * @author 김지훈
     */
    @GetMapping("/display")
    public ResponseEntity<Resource> display(@RequestParam("fid") Integer fid) {
        log.info("display(fid={})",fid);
        
        Images image = imagesService.readByFid(fid);
        String path = image.getFileUrl();        
        
        File file = new File(path);
        
        Path source = Paths.get(path);
        String headerString = null;
        try {
            headerString = Files.probeContentType(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Resource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", headerString);
        
        log.info("resource ={}, header={}", resource, headers);
        return ResponseEntity.ok().headers(headers).body(resource);
    }
    
    
}