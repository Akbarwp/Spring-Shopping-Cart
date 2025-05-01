package com.spring.springshoppingcart.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.springshoppingcart.dto.ImageDto;
import com.spring.springshoppingcart.model.Image;

public interface IImageService {
    Image getImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long productId);
    void deleteImageById(Long id);
}
