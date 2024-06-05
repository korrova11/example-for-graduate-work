package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.*;
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

    @Test
    void createOrUpdateToAdEntity(){
        CreateOrUpdateAd createOrUpdateAd = CreateOrUpdateAd.builder()
                .title("Заголовок")
                .price(20000)
                .description("Описаниееее")
                .build();

        AdEntity adEntity = AdMapper.INSTANCE.createOrUpdateToAdEntity(createOrUpdateAd);
        assertThat(adEntity.getTitle()).isEqualTo("Заголовок");
        assertThat(adEntity.getPrice()).isEqualTo(20000);
        assertThat(adEntity.getDescription()).isEqualTo("Описаниееее");
    }

    @Test
    void adEntityToExtendedAd(){
        AdEntity adEntity = AdEntity.builder()
                .id(1L)
                .user(UserEntity.builder()
                        .firstName("Имя")
                        .lastName("Фамилия")
                        .phone("+7384)18239-64")
                        .login("Логин")
                        .build())
                .price(1000)
                .description("Описание")
                .title("Заголовок")
                .build();

        ExtendedAd extendedAd = AdMapper.INSTANCE.adEntityToExtendedAd(adEntity);
        assertThat(extendedAd.getPk()).isEqualTo(1);
        assertThat(extendedAd.getPrice()).isEqualTo(1000);
        assertThat(extendedAd.getTitle()).isEqualTo("Заголовок");
        assertThat(extendedAd.getAuthorFirstName()).isEqualTo("Имя");
        assertThat(extendedAd.getAuthorLastName()).isEqualTo("Фамилия");
        assertThat(extendedAd.getEmail()).isEqualTo("Логин");
        assertThat(extendedAd.getPhone()).isEqualTo("+7384)18239-64");
        assertThat(extendedAd.getDescription()).isEqualTo("Описание");
    }


}