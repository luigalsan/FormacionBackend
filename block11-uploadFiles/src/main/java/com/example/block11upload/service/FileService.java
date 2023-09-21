package com.example.block11upload.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String uploadFile(MultipartFile file, String categoria, String tipo);
    public void setFileUploadLocation(String fileUploadLocation);
    public InputStreamResource downloadFile(String fileName);

}
