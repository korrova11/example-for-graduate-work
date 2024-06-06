package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.service.AdService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
@Transactional
public class AdServiceImpl implements AdService {
    @Value("${path.to.photo.folder}")
    private String avatarsDir;
    private final AdRepository repository;
    private final ImageEntityRepository imageEntityRepository;
    private final UserServiceImpl userService;
    private final CommentRepository commentRepository;

    /**
     * метод добавляет объявление и загружает картинку для этого объявления
     *
     * @param image
     * @param properties
     * @param authentication
     * @return возвращает сущность Ad (dto)
     * @throws IOException
     */
    public Ad addAd(MultipartFile image, CreateOrUpdateAd properties
            , Authentication authentication) throws IOException {

        AdEntity adEntity = AdMapper.INSTANCE.createOrUpdateToAdEntity(properties);
        //adEntity.setId(1L);
        adEntity.setUser(userService.findByLogin(authentication.getName()).get());
        AdEntity adEntity1 = repository.save(adEntity);
        uploadImageForAd(adEntity1.getId(), image);

        return AdMapper.INSTANCE.adEntityToAd(adEntity1);

    }

    /**
     * метод меняет поля объявления
     *
     * @param id
     * @param properties
     * @param authentication
     * @return возвращает Optional cущности Ad ( dto)
     */

    public Optional<Ad> changeAd(Integer id, CreateOrUpdateAd properties
            , Authentication authentication) {
        Optional<AdEntity> adEntity = repository.findById(id.longValue());
        if (adEntity.isEmpty()) return Optional.empty();

        adEntity.get().setPrice(properties.getPrice());
        adEntity.get().setDescription(properties.getDescription());
        adEntity.get().setTitle(properties.getTitle());
        return Optional.of(AdMapper.INSTANCE.adEntityToAd(adEntity.get()));


    }

    /**
     * метод загружает картиинку объявления
     *
     * @param id
     * @param image
     * @throws IOException
     */

    public void uploadImageForAd(Long id, MultipartFile image) throws IOException {

        AdEntity ad = findById(id).get();
        Path filePath = Path.of(avatarsDir, ad.getId() + "." + getExtensions(image.getOriginalFilename()));
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

        ImageEntity imageEntity = Optional.ofNullable(ad.getImageEntity())
                .orElse(new ImageEntity());

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

    /**
     * метод возвращает все объявления
     *
     * @return
     */


    public Ads getAll() {
        List<Ad> list = repository.findAll().stream()
                .map(a -> AdMapper.INSTANCE.adEntityToAd(a)).collect(Collectors.toList());
        return new Ads(list.size(), list);
    }

    /**
     * метод возвращает Optional объявления по Id
     *
     * @param id
     * @return
     */
    public Optional<AdEntity> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * метод проверяет содержание в репозитории объявления
     * с указанным id и удаляет его и все комментарии к нему
     *
     * @param id
     * @return
     */
    public boolean deleteAd(Integer id) {
        Optional<AdEntity> adEntity = repository.findById(id.longValue());
        if (adEntity.isEmpty()) {
            return false;
        } else {
            AdEntity ad = adEntity.get();
            repository.getReferenceById(id.longValue()).getComments()
                    .forEach(commentRepository::delete);
             if (Optional.ofNullable(ad.getImageEntity().getId()).isPresent()){
                 imageEntityRepository.delete(ad.getImageEntity());
             }

            repository.deleteById(id.longValue());
            return true;
        }
    }

    /**
     * метод возвращает дто объекта AdEntity по id
     *
     * @param id
     * @return
     */

    public ExtendedAd getById(Integer id) {
        Optional<AdEntity> ad = repository.findById(id.longValue());
        if (ad.isPresent()) {
            return AdMapper.INSTANCE.adEntityToExtendedAd(ad.get());
        } else return null;
    }

    /**
     * метод возвращает все объявления владельца
     *
     * @param authentication
     * @return
     */
    public Ads getAdsByUser(Authentication authentication) {
        List<Ad> list = repository.findAll().stream()
                .filter(adEntity -> (adEntity.getUser().getLogin()).equals(authentication.getName()))
                .map(a -> AdMapper.INSTANCE.adEntityToAd(a)).collect(Collectors.toList());
        return new Ads(list.size(), list);
    }

    /**
     * метод меняет картинку объявления
     *
     * @param id
     * @param image
     * @throws IOException
     */
    public void changeImageAd(Long id, MultipartFile image)
            throws IOException {

        uploadImageForAd(id, image);

    }

    /**
     * метод возвращает true , если запрос поступает от собственника или админа.
     *
     * @param id
     * @param authentication
     */
    public boolean isMainOrAdmin(Integer id, Authentication authentication) {
        boolean admin = (userService.findByLogin(authentication.getName()).get().getRole()) == Role.ADMIN;
        return (authentication.getName()).equals(findById(id.longValue()).get().getUser().getLogin()) || admin;


    }

}
