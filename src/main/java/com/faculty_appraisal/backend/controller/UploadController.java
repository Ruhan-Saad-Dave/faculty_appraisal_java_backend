package com.faculty_appraisal.backend.controller;

import com.faculty_appraisal.backend.model.dto.UploadResponse;
import com.faculty_appraisal.backend.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UploadController extends BaseController {

    private final StorageService storageService;

    @PostMapping("/upload")
    public UploadResponse upload(
            @RequestPart("file") MultipartFile file,
            @RequestParam(required = false) String folder
    ) throws IOException {
        return storageService.upload(file, folder, currentUser().getEmail());
    }
}
