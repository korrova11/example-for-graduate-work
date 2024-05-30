package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.service.AdService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository repository;
    //private final ImageServiceImpl imageService;
    private final ImageEntityRepository imageEntityRepository;

    public Ad addAd(MultipartFile image, CreateOrUpdateAd properties) throws IOException {
        AdEntity adEntity = AdMapper.INSTANCE.createOrUpdateToAdEntity(properties);
        repository.save(adEntity);
        uploadImageForAd(repository.save(adEntity).getId(),image);

        return AdMapper.INSTANCE.adEntityToAd(adEntity);

    }
    public void uploadImageForAd(Long id, MultipartFile image) throws IOException {

        AdEntity ad = findById(id);
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
        ImageEntity imageEntity = imageEntityRepository.findById(ad.getImageEntity().getId()).orElse(new ImageEntity());
        imageEntity.setFilePath(filePath.toString());
        imageEntity.setFileSize(image.getSize());
        imageEntity.setMediaType(image.getContentType());
        imageEntity.setData(image.getBytes());
        imageEntityRepository.save(imageEntity);
        ad.setImageEntity(imageEntity);
    }
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }



    public Ads getAll() {
        List<Ad> list = repository.findAll().stream()
                .map(a -> AdMapper.INSTANCE.adEntityToAd(a)).collect(Collectors.toList());
        return new Ads(list.size(), list);
    }


    public AdEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteAd(Integer id) {
        repository.deleteById(id.longValue());

    }

    public ExtendedAd getById(Integer id) {
        Optional<AdEntity> ad= repository.findById(id.longValue());
        if (ad.isPresent()){return AdMapper.INSTANCE.adEntityToExtendedAd(ad.get());}
        else throw new NotFoundException("Отсутствует в базе");
    }

}
