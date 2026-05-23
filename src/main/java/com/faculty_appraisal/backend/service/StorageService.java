package com.faculty_appraisal.backend.service;

import com.faculty_appraisal.backend.exception.AppException;
import com.faculty_appraisal.backend.model.dto.UploadResponse;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class StorageService {

    @Value("${GCP_STORAGE_BUCKET:}")
    private String gcpBucket;

    @Value("${USE_LOCAL_STORAGE:false}")
    private boolean useLocalStorage;

    @Value("${LOCAL_STORAGE_DIR:./uploads}")
    private String localStorageDir;

    public UploadResponse upload(MultipartFile file, String folder, String userEmail) throws IOException {
        byte[] fileBytes = file.getBytes();
        String contentType = file.getContentType() != null
                ? file.getContentType() : "application/octet-stream";
        String safeName = (file.getOriginalFilename() != null
                ? file.getOriginalFilename() : "file").replace(" ", "_");

        // PDF-only: no lossless optimisation step (no pikepdf equivalent).
        // SHA-256 of raw bytes is the deduplication key.
        String contentHash = sha256Hex(fileBytes);

        String objectKey = (folder != null && !folder.isBlank())
                ? folder + "/" + contentHash + "_" + safeName
                : "faculty/" + userEmail + "/" + contentHash + "_" + safeName;

        // Mock mode — no GCS bucket configured
        if (gcpBucket == null || gcpBucket.isBlank()) {
            return UploadResponse.builder()
                    .url("https://storage.example.com/" + objectKey)
                    .publicId(objectKey)
                    .name(file.getOriginalFilename())
                    .type(contentType)
                    .deduped(false)
                    .build();
        }

        // Local storage fallback
        if (useLocalStorage) {
            String baseDir = (folder != null && !folder.isBlank())
                    ? folder : "faculty/" + userEmail;
            Path targetDir = Paths.get(localStorageDir, baseDir);
            Files.createDirectories(targetDir);
            Path localPath = targetDir.resolve(contentHash + "_" + safeName);
            boolean deduped = Files.exists(localPath);
            if (!deduped) {
                Files.write(localPath, fileBytes);
            }
            String rel = Paths.get(localStorageDir).relativize(localPath)
                    .toString().replace("\\", "/");
            return UploadResponse.builder()
                    .url("/uploads/" + rel)
                    .publicId(rel)
                    .name(file.getOriginalFilename())
                    .type(contentType)
                    .deduped(deduped)
                    .build();
        }

        // GCS — uses Application Default Credentials (auto on Cloud Run)
        try {
            Storage storage = StorageOptions.getDefaultInstance().getService();
            BlobId blobId = BlobId.of(gcpBucket, objectKey);
            Blob existing = storage.get(blobId);
            if (existing == null) {
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                        .setContentType(contentType)
                        .build();
                storage.create(blobInfo, fileBytes);
            }
            String publicUrl = "https://storage.googleapis.com/" + gcpBucket + "/" + objectKey;
            return UploadResponse.builder()
                    .url(publicUrl)
                    .publicId(objectKey)
                    .name(file.getOriginalFilename())
                    .type(contentType)
                    .build();
        } catch (Exception e) {
            log.error("GCS upload failed: {}", e.getMessage(), e);
            throw new AppException(500, "Your file could not be uploaded. Please try again.");
        }
    }

    private String sha256Hex(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            StringBuilder sb = new StringBuilder(64);
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
