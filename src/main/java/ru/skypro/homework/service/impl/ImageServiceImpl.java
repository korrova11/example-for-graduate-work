package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageEntityRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RequiredArgsConstructor
@Service
@Transactional
public class ImageServiceImpl {

    private final ImageEntityRepository repository;
    @Value(value = "${path.to.image.folder}")
    private String photoDir;



    public byte[] getImage(Integer id) {
        Optional<ImageEntity> imageEntityOptional = repository.findById(id.longValue());
        if (imageEntityOptional.isEmpty()) {
            return null;
        }
        ImageEntity imageEntity = imageEntityOptional.get();
        byte[] res = getImage(imageEntity.getName(), photoDir);
        return res;
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

        public  ImageEntity loadImage(MultipartFile file, String pathDir) throws IOException {
            String fileName = UUID.randomUUID() + "." + getExtensions(file.getOriginalFilename());
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(fileName);
            Path filePath = Path.of(pathDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            try (InputStream is = file.getInputStream();
                 OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024)){
                bis.transferTo(bos);
            }
            return imageEntity;
        }


        public  void deleteFile(String url) throws IOException {
            Path path = Path.of(url);
            Files.deleteIfExists(path);
        }

        public  byte[] getImage(String fileName, String pathDir) {
            Path filePath = Path.of(pathDir + "\\" + fileName);
            try (InputStream is = Files.newInputStream(filePath);
                 BufferedInputStream bis = new BufferedInputStream(is, 1024);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                bis.transferTo(baos);
                return baos.toByteArray();
            } catch (IOException e) {
                return null;
            }
        }


}
