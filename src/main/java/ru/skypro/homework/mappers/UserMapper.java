package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "email", source = "login"),
            @Mapping(target = "image", expression = "java(userEntity.getImageEntity()==null?\"\":\"/image/download/\"+userEntity.getImageEntity().getId())")
    })
    User userEntityToUser(UserEntity userEntity);

    @Mapping(target = "imageEntity", ignore = true)
    @Mapping(target = "login", source = "username")
    @Mapping(target = "id", ignore = true)
    UserEntity registerToUserEntity(Register register);

    UpdateUser userEntityToUpdateUser(UserEntity user);


    //UserEntity doUpdateUserToUserEntity(UpdateUser updateUser);

}
