package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.entity.ImageEntity;

@Mapper(componentModel = "spring")
@Component
public interface ImageMapper {
    /*ImageMapper INSTANCEIM = Mappers.getMapper(ImageMapper.class);

    Image imageEntityToImage(ImageEntity imageEntity);
    ImageEntity imageToImageEntity(Image image);*/
}
