package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageMapperTest {
    @Test
    public void shouldImageToImageEntity(){
        Image image = Image.builder()
                .id(6L)
                .fileSize(78L)
                .build();
      /*  ImageEntity imageEntity1 = ImageEntity.builder()
                .id(6L)
                .fileSize(78L)
                .build();

        ImageEntity imageEntity = ImageMapper.INSTANCEIM.imageToImageEntity(image);
       // Image image1 = ImageMapper.INSTANCEIM.imageEntityToImage(imageEntity1);
        assertThat(imageEntity.getId()).isEqualTo(6L);
      //  assertThat(user.getFirstName()).isEqualTo("имя");
*/
    }
}
