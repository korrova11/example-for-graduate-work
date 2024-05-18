package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.Ad;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;

public interface AdService {
    Ad addAd (MultipartFile image, CreateOrUpdateAd properties);


}
