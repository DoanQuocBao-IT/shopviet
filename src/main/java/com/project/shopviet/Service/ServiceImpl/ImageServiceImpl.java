package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${path.upload}")
    private String UPLOAD_DIR;
    @Override
    public String saveImage(String base64String) {
        try {
            String base64Image = base64String.split(",")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
            String imageName= UUID.randomUUID().toString()+".png";
            Path path = Paths.get(UPLOAD_DIR+imageName);
            Files.write(path,decodedBytes);
            return imageName;
        } catch (Exception e) {
            throw new RuntimeException("Could not save image", e);
        }
    }

    @Override
    public String deleteImage(String imageName) {
        try {
            Path path = Paths.get(UPLOAD_DIR+imageName);
            Files.delete(path);
            return "Delete image success";
        } catch (Exception e) {
            throw new RuntimeException("Could not delete image", e);
        }
    }
}
