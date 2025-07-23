package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/media")
public class MediaController {
    @Value("${media.upload.dir:uploads}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedia(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        File dest = new File(uploadDir, filename);
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
        // Return the static URL to access the file
        String fileUrl = "/media/" + filename;
        return ResponseEntity.ok(fileUrl);
    }
}
