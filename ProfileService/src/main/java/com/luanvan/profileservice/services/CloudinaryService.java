package com.luanvan.profileservice.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file, String folder) {
        try {
            Map uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(),
                            ObjectUtils.asMap(
                                    "folder", folder,
                                    "resource_type", "auto"
                            ));
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not upload file", e);
        }
    }

    public void deleteFile(String publicUrl) {
        try {
            // Trích xuất public_id từ URL
            String publicId = extractPublicIdFromUrl(publicUrl);
            if (publicId != null) {
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file", e);
        }
    }

    private String extractPublicIdFromUrl(String url) {
        if (url == null || url.isEmpty()) return null;
        try {
            String[] parts = url.split("/upload/");
            if (parts.length > 1) {
                // Lấy phần sau /upload/ và loại bỏ version number nếu có
                String path = parts[1].replaceFirst("v\\d+/", "");
                // Loại bỏ phần mở rộng file
                return path.substring(0, path.lastIndexOf('.'));
            }
        } catch (Exception e) {
            // Nếu có lỗi khi parse URL, return null
            return null;
        }
        return null;
    }

    public String uploadChungChi(MultipartFile file, String maSo) {
        String folder = String.format("certificate/%s", maSo);
        try {
            Map uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(),
                            ObjectUtils.asMap(
                                    "folder", folder,
                                    "resource_type", "auto"
                            ));
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Không thể upload chứng chỉ", e);
        }
    }
}
