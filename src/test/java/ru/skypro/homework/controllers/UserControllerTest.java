package ru.skypro.homework.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(WebSecurityConfig.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ImageEntityRepository imageRepository;

    @SpyBean
    private UserServiceImpl userService;
    @SpyBean
    private AuthServiceImpl authService;
    @Autowired
    PasswordEncoder encoder;
    @SpyBean
    MyUserDetailsService myUserDetailsService;


    @InjectMocks
    private UserController userController;
    String login = "LOGIN";
    Long id = 34L;
    String firstName = "ИМЯ";
    String lastName = "ФАМИЛИЯ";
    String phone = "+7(6978548616";
    UserEntity userEntity = UserEntity.builder()
            .firstName(firstName)
            .lastName(lastName)
            .id(id)
            .login(login)
            .password(encoder.encode("12345678"))
            .build();
    NewPassword newPassword = NewPassword.builder()
            .currentPassword("12345678")
            .newPassword("87654321")
            .build();
    UpdateUser updateUser = UpdateUser.builder()
            .phone("+7(6978548616")
            .lastName("НоваяФамилия")
            .firstName("НовоеИмя")
            .build();

    @Test
    @WithMockUser(value = "spring")
    public void getUserTest() throws Exception {

        when(userRepository.findByLogin(any(String.class)))
                .thenReturn(Optional.of(userEntity));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("ИМЯ"))
                .andExpect(jsonPath("$.email").value("LOGIN"));


    }

    @Test
    @WithAnonymousUser
    void cannotUserIfNotAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(value = "spring")
    public void setPasswordTest() throws Exception {
        NewPassword newPassword = NewPassword.builder()
                .currentPassword("12345678")
                .newPassword("87654321")
                .build();

        JSONObject updateUserObject = new JSONObject();
        updateUserObject.
        when(userRepository.findByLogin(any(String.class)))
                .thenReturn(Optional.of(userEntity));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/set_password", newPassword)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(value = "spring")
    public void changeUserTest() throws Exception {
        JSONObject updateUserObject = new JSONObject();
        updateUserObject.put("phone", phone);
        updateUserObject.put("lastName", "НоваяФамилия");
        updateUserObject.put("firstName", "НовоеИмя");
        when(userRepository.findByLogin(any(String.class)))
                .thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/me", updateUser)
                        .content(updateUserObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.lastName").value("НоваяФамилия"))
                .andExpect(jsonPath("$.firstName").value("НовоеИмя"));
        ;

    }
}
