package ru.skypro.homework.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AvatarRepository;

public abstract class UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
private AvatarRepository avatarRepository;
    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "email",source = "login"),
            @Mapping(target = "role",source = "role"),
        //    @Mapping(target = "image",expression = "avatarRepository.findByUserEntityId(entity.getId")
    })
    public abstract User userEntityToUser(UserEntity userEntity);
}
