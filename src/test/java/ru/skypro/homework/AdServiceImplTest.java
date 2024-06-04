package ru.skypro.homework;

import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.util.Collection;

@AllArgsConstructor
public class AdServiceImplTest {
    AdServiceImpl service;

    @Test
    public void adAddTest() {
        CreateOrUpdateAd createOrUpdateAd = CreateOrUpdateAd.builder()
                .price(200)
                .description("описание")
                .build();
        Image image = Image.builder()

                .filePath("/ооо")
                .fileSize(200L)
                .build();
       /* Authentication authentication = new Authentication() ;

        Assertions.assertEquals(service.addAd(image, createOrUpdateAd, A));
*/

    }
}
