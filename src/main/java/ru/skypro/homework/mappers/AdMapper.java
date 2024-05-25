package ru.skypro.homework.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
@Component
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mappings({
            @Mapping(target = "author", source = "adEntity.user.id"),
            @Mapping(target = "pk", source = "id"),
    })
    Ad adEntityToAd(AdEntity adEntity);

}
