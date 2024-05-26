package ru.skypro.homework.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
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

    @Mappings({
            @Mapping(target = "description", source = "description"),
    })
    AdEntity createOrUpdateToAdEntity (CreateOrUpdateAd createOrUpdateAd);

    @Mappings({
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "pk", source = "id"),
            @Mapping(target = "authorFirstName", source = "user.firstName"),
            @Mapping(target = "authorLastName", source = "user.lastName"),
            @Mapping(target = "email", source = "user.login"),
            @Mapping(target = "phone", source = "user.phone"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "title", source = "title"),
    })
    ExtendedAd adEntityToExtendedAd (AdEntity adEntity);


}
