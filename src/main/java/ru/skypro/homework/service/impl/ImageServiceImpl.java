package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdImageEntity;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdImageEntityRepository;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@AllArgsConstructor
@Service
@Transactional
public class ImageServiceImpl {
    private final AdImageEntityRepository adImageEntityRepository;
    private final AvatarRepository avatarRepository;
    private final UserServiceImpl userService;
    private final AdServiceImpl adService;

    public void uploadImage(Long id, MultipartFile image) throws IOException {
        // logger.info("Was invoked method for uploadAvatar");
        UserEntity user = userService.findById(id);
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
        Avatar avatar = findAvatarByStudentId(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        // avatar.setData(avatarFile.getBytes());
        avatar.setData(generateDataForDB(filePath));
        avatarRepository.save(avatar);
    }


    private String getExtensions(String fileName) {

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public ImageEntity findImageByUserEntityId(Long id) {
        return avatarRepository.findByUserEntityId(id).orElse(new AvatarEntity());
    }

    public ImageEntity findImageByAdEntityId(Long id) {
        return adImageEntityRepository.findById(id).orElse(new AdImageEntity());
    }
}
