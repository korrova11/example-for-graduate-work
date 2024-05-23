package ru.skypro.homework.mappers;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
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


        UserEntity userEntity=UserMapper.INSTANCE.registerToUserEntity(register);


        assertThat( userEntity.getFirstName()).isEqualTo( "имя" );

    }


}
