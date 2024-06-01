package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@AllArgsConstructor
public class UserServiceImpl  {
    private final UserRepository repository;
    private final ImageEntityRepository imageEntityRepository;

    public boolean changePassword(NewPassword changePassword) {
        return true;
    }

    public UserEntity findById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public User getUserDto() {
        return null;
    }
    public Optional<UserEntity> findByLogin(String login){
        return repository.findByLogin(login);
    }
    public boolean adUser(Register register){
        UserEntity userEntity = UserMapper.INSTANCE.registerToUserEntity(register);
        if((repository.save(userEntity).getLogin()).equals(register.getUsername())) {
            return true;
        }
        else return false;

    }
    public User getUser(Authentication authentication){
        if (findByLogin(authentication.getName()).isEmpty()) return null;
        else return UserMapper.INSTANCE
                .userEntityToUser(findByLogin(authentication.getName()).get());
    }


    public void uploadImageForUser(String login, MultipartFile image) throws IOException {
        UserEntity user = findByLogin(login).get();
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
        ImageEntity imageEntity = imageEntityRepository.findById(user.getImageEntity().getId()).orElse(new ImageEntity());
        imageEntity.setFilePath(filePath.toString());
        imageEntity.setFileSize(image.getSize());
        imageEntity.setMediaType(image.getContentType());
        imageEntity.setData(image.getBytes());
        imageEntityRepository.save(imageEntity);
        user.setImageEntity(imageEntity);
    }
    private String getExtensions(String fileName) {

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    public boolean changeUser(UpdateUser updateUser, Authentication authentication){
        UserEntity userEntity = repository.findByLogin(authentication.getName()).get();
        userEntity.setPhone(updateUser.getPhone());
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        repository.save(userEntity);
        return true;
    }

}

