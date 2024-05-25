package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdMapperTest {

    @Test
    void adEntityToAd() {

        AdEntity adEntity = AdEntity.builder().
                id(1L).
                user(UserEntity.builder()
                        .id(3L)
                        .build())
                .build();

        Ad ad = AdMapper.INSTANCE.adEntityToAd(adEntity);
        assertThat(ad.getPk()).isEqualTo(1);
        assertThat(ad.getAuthor()).isEqualTo(3);


    }
}