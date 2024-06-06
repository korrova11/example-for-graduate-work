package ru.skypro.homework.controllers;

import net.minidev.json.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(WebSecurityConfig.class)
@WebMvcTest(AdController.class)
public class AdControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdRepository adRepository;
    @MockBean
    private ImageEntityRepository imRepository;
    @MockBean
    private UserRepository usRepository;
    @MockBean
    private CommentRepository comRepository;
    @SpyBean
    private AdServiceImpl adService;
    @SpyBean
    private UserServiceImpl usService;
    @SpyBean
    private MyUserDetailsService myUserDetailsService;
    @InjectMocks
    private AdController adController;
    AdEntity adEntity1 = AdEntity.builder()
            .description("Описание1")
            .title("Заголовок1")
            .price(77)
            .build();
    AdEntity adEntity2 = AdEntity.builder()
            .description("Описание2")
            .title("Заголовок2")
            .build();
    List<AdEntity> list = new ArrayList<>(List.of(adEntity1, adEntity2));
    UserEntity userEntity = UserEntity.builder()
            .login("ИМЯ")
            .build();
    Authentication authentication = new TestAuthor("ИМЯ");

    @Test
    @WithMockUser(value = "spring")
    public void getAllTest() throws Exception {
        when(adRepository.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(value = "spring")
    public void getAdTest() throws Exception {
        when(adRepository.findById(any(Long.class))).thenReturn(Optional.of(adEntity1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/1") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Заголовок1"))
                .andExpect(jsonPath("$.description").value("Описание1"));
    }

    @Test
    @WithMockUser(value = "spring")
    public void getAdWhenNotFoundTest() throws Exception {
        when(adRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/1") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
   /* @Test
    //@WithMockUser(value = "spring")
    public void updateAdsTest() throws Exception {
        JSONObject updateUserObject = new JSONObject();
        updateUserObject.put("title", "НовыйЗаголовок");
        updateUserObject.put("description", "НовоеОписание");
        updateUserObject.put("price", 45);
        when(adRepository.findById(any(Long.class))).thenReturn(Optional.of(adEntity1));
        when(usRepository.findByLogin(any(String.class))).thenReturn(Optional.of(userEntity));
        when(adService.isMainOrAdmin(any(Integer.class),eq(authentication)));
        when(usService.findByLogin(eq(authentication.getName())))
                .thenReturn(Optional.of(userEntity));
        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/ads/1")
                        .content(updateUserObject.toString())//send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("НовыйЗаголовок"))
                .andExpect(jsonPath("$.price").value(45))
                .andExpect(jsonPath("$.description").value("НовоеОписание"));
    }*/
}
