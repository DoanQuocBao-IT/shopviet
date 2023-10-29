package com.project.shopviet.Service;

public interface ImageService {
    String saveImage(String base64String);
    boolean deleteImage(String imageName);
}
