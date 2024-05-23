package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserMapperTest {

    @Test
    public void shouldRegisterToUserEntity() {

        Register register = Register.builder()
                .firstName("имя")
                .lastName("фамилия")
                .phone("555")
                .role(Role.USER)
                .password("пароль")
                .username("email")
                .build();

        UserEntity userEntity = UserMapper.INSTANCE.registerToUserEntity(register);

        assertThat(userEntity.getFirstName()).isEqualTo("имя");
        assertThat(userEntity.getLogin()).isEqualTo("email");
        assertThat(userEntity.getLastName()).isEqualTo("фамилия");
        assertThat(userEntity.getPhone()).isEqualTo("555");
        assertThat(userEntity.getRole()).isEqualTo(Role.USER);

    }
    @Test
    public void shouldUserEntityToUser(){
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .lastName("фамилия")
                .firstName("имя")
                .phone("111")
                .login("login")
                .build();
        User user = UserMapper.INSTANCE.userEntityToUser(userEntity);
        assertThat(user.getEmail()).isEqualTo("login");
        assertThat(user.getFirstName()).isEqualTo("имя");
        assertThat(user.getPhone()).isEqualTo("111");
        assertThat(user.getLastName()).isEqualTo("фамилия");
    }


}
