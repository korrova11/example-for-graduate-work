package ru.skypro.homework.controllers;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.config.WebSecurityConfig;
import ru.skypro.homework.controller.AdController;
import ru.skypro.homework.controller.CommentController;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.ImageEntityRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;
import ru.skypro.homework.service.impl.CommentServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(WebSecurityConfig.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {
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
    private CommentServiceImpl comService;
    @SpyBean
    private MyUserDetailsService myUserDetailsService;
    @InjectMocks
    private AdController adController;
    UserEntity userEntity1 = UserEntity.builder()
            .role(Role.ADMIN)
            .login("ИМЯ")
            .build();
    UserEntity userEntity2 = UserEntity.builder()
            .role(Role.USER)
            .login("ИМЯ")
            .build();
    AdEntity adEntity1 = AdEntity.builder()
            .user(userEntity1)
            .id(1L)
            .description("Описание1")
            .title("Заголовок1")
            .price(77)
            .build();
    AdEntity adEntity2 = AdEntity.builder()
            .id(2L)
            .user(userEntity1)
            .description("Описание2")
            .title("Заголовок2")
            .build();
    CommentEntity commentEntity1 = CommentEntity.builder()
            .id(1)
            .text("Комментарий1")
            .createdAt(new Date())
            .userEntity(userEntity1)
            .ads(adEntity1)
            .build();
    CommentEntity commentEntity2 = CommentEntity.builder()
            .id(2)
            .text("Комментарий2")
            .createdAt(new Date())
            .userEntity(userEntity1)
            .ads(adEntity1)
            .build();
    CommentEntity commentEntity3 = CommentEntity.builder()
            .id(3)
            .text("Комментарий3")
            .createdAt(new Date())
            .userEntity(userEntity1)
            .ads(adEntity2)
            .build();
    CommentEntity commentEntity4 = CommentEntity.builder()
            .text("НовыйКомментарий")
            .build();
    List<CommentEntity> list =
            new ArrayList<>(List.of(commentEntity1, commentEntity2, commentEntity3));

    @Test
    @WithMockUser(value = "spring")
    public void getCommentsTest() throws Exception {
        when(comRepository.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/1/comments") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2));

    }

    @Test
    @WithMockUser(value = "spring")
    public void getCommentsWhenNotFoundTest() throws Exception {
        when(comRepository.findAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ads/1/comments") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }

    @Test
    @WithMockUser(value = "spring")
    public void addCommentTest() throws Exception {
        JSONObject updateCommentObject = new JSONObject();
        updateCommentObject.put("text", "НовыйКомментарий");


        when(adRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(adEntity1));
        when(usRepository.findByLogin(any(String.class))).thenReturn(Optional.of(userEntity1));
        when(comRepository.save(any(CommentEntity.class))).thenReturn(commentEntity1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ads/1/comments")
                        .content(updateCommentObject.toString())//send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}




