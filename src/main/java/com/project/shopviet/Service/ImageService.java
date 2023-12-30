package com.project.shopviet.Service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface ImageService {
    String saveImage(String base64String);
    boolean deleteImage(String imageName);
    ResponseEntity<Resource> getImage(String imageName);
}
