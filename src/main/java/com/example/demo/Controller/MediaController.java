package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Value("${media.upload.dir:uploads}")
    private String uploadDir;

    @PostMapping("/upload/{type}")
    public ResponseEntity<String> uploadMedia(
            @PathVariable("type") String type,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        System.out.println("Received upload for type: " + type);
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        // Create a subdirectory based on the type (e.g., uploads/post/)
        File destDir = new File(uploadDir, type);
        destDir.mkdirs(); // Ensure the directory exists

        File dest = new File(destDir, filename);
        System.out.println("Saving file to: " + dest.getAbsolutePath());

        file.transferTo(dest);

        // Return static URL like: /media/post/filename.jpg
        String fileUrl = "/media/" + type + "/" + filename;
        return ResponseEntity.ok(fileUrl);
    }
}
