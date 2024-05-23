package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AvatarRepository;
@Mapper(componentModel = "spring")
public interface UserMapper {
   UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
   // private AvatarRepository avatarRepository;

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "email", source = "login"),
            @Mapping(target = "role", source = "role"),
         //   @Mapping(target = "image", expression = "avatarRepository.findByUserEntityId(userEntity.getId).get()")
    })
     User userEntityToUser(UserEntity userEntity);
@Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "login", source = "username"),
            @Mapping(target = "role", source = "role"),
            @Mapping(target = "password", source = "password"),
    })
    UserEntity registerToUserEntity(Register register);
}
