package com.example.generator.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/images")
public class ImageController {

    // 自己写了个get方法，避开tomcat的临时目录
    @GetMapping("/PostImages/{filename:.+}")
    public ResponseEntity<Resource> getPostImage(@PathVariable String filename) throws IOException {
        String projectRootPath = System.getProperty("user.dir");
        String basePath = projectRootPath + "/src/main/resources/static/images/PostImages/";

        File file = new File(basePath + filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }

        Resource resource = new FileSystemResource(file);

        // Set content type based on file extension
        HttpHeaders headers = new HttpHeaders();
        String contentType = Files.probeContentType(file.toPath());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
