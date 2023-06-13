package com.example.generator.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {

    // 自己写了个get方法，避开tomcat的临时目录。可以获得Avatar或者PostImages
    @GetMapping("/{directoryname}/{filename:.+}")
    public ResponseEntity<Resource> getPostImage(@PathVariable String directoryname, @PathVariable String filename) throws IOException {
        String projectRootPath = System.getProperty("user.dir");
        String basePath = projectRootPath + "/src/main/resources/static/images/"+directoryname+"/";

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

    @PostMapping("/uploadAvatar")
    public ResponseEntity<Map<String, Object>> uploadAvatar(MultipartHttpServletRequest request) {
        try {
            List<MultipartFile> files = request.getFiles("file");
            String projectRootPath = System.getProperty("user.dir");
            String basePath = projectRootPath + "/src/main/resources/static/images/Avatar/";

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                    String name = "avatar_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    String newFilename = name + "." + ext;

                    String dst = basePath + newFilename;
                    String fileUrl = "/images/Avatar/" + newFilename;
                    File destFile = new File(dst);
                    if (!destFile.getParentFile().exists()) {
                        destFile.getParentFile().mkdirs();
                    }
                    file.transferTo(destFile);

                    return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, Object>() {{
                        put("code", 200);
                        put("data", new HashMap<String, String>() {{
                            put("avatarURL", "http://localhost:8080"+fileUrl);
                            put("phone", "");
                        }});
                        put("msg", "上传成功");
                    }});
                }
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, Object>() {{
                put("code", 500);
                put("errno", 1);
                put("msg", "上传失败");
            }});
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, Object>() {{
                put("code", 501);
                put("errno", 1);
                put("msg", "上传失败");
            }});
        }
    }


}
