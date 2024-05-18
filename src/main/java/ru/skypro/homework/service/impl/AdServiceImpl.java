package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.service.AdService;

@Service
public class AdServiceImpl implements AdService {

    public Ad addAd (MultipartFile image, CreateOrUpdateAd properties){
        return null;
    }

}
