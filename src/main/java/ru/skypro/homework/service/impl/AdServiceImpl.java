package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.Ad;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@Service
public class AdServiceImpl implements AdService {

    public Ad addAd (MultipartFile image, CreateOrUpdateAd properties){
        return null;
    }

}
