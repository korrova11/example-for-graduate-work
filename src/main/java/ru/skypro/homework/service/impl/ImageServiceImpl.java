package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageEntityRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@AllArgsConstructor
@Service
@Transactional
public class ImageServiceImpl {

    private final UserServiceImpl userService;
   // private final AdServiceImpl adService;
    private final ImageEntityRepository repository;

    public void uploadImageForUser(String login, MultipartFile image) throws IOException {

        UserEntity user = userService.findByLogin(login);
        Path filePath = Path.of("/image", user + "." + getExtensions(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());

        Files.deleteIfExists(filePath);
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        ImageEntity imageEntity = repository.findById(user.getImageEntity().getId()).orElse(new ImageEntity());
        imageEntity.setFilePath(filePath.toString());
        imageEntity.setFileSize(image.getSize());
        imageEntity.setMediaType(image.getContentType());
        imageEntity.setData(image.getBytes());
        repository.save(imageEntity);
        user.setImageEntity(imageEntity);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    /*public void uploadImageForAd(Long id, MultipartFile image) throws IOException {

        AdEntity ad = adService.findById(id);
        Path filePath = Path.of("/image", ad + "." + getExtensions(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        ImageEntity imageEntity = repository.findById(ad.getImageEntity().getId()).orElse(new ImageEntity());
        imageEntity.setFilePath(filePath.toString());
        imageEntity.setFileSize(image.getSize());
        imageEntity.setMediaType(image.getContentType());
        imageEntity.setData(image.getBytes());
        repository.save(imageEntity);
        ad.setImageEntity(imageEntity);
    }
*/

}
