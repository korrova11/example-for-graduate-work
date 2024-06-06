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





    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
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
