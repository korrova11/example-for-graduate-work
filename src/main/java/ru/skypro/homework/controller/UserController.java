package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.skypro.homework.service.impl.UserServiceImpl;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @Operation(
            summary = "Обновление пароля",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NewPassword.class)
                    )
            ),
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )},
            tags = "Пользователи"
    )


    @PostMapping("/set_password")
    public void setPassword(@RequestBody NewPassword changePassword) {
        if (!userService.validationPassword(changePassword)) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else if (userService.changePassword(changePassword)) {
            ResponseEntity.ok().build();
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            responses = {@ApiResponse(
                    responseCode = "200",

                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject("{\n" +
                                    "  \"id\": 0,\n" +
                                    "  \"email\": \"string\",\n" +
                                    "  \"firstName\": \"string\",\n" +
                                    "  \"lastName\": \"string\",\n" +
                                    "  \"phone\": \"string\",\n" +
                                    "  \"role\": \"USER\",\n" +
                                    "  \"image\": \"string\"\n" +
                                    "}")
                    )

            ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"

                    )},
            tags = "Пользователи"
    )
    @GetMapping("/me")
    public ResponseEntity<?> getUser(Authentication authentication) {

        if (1 == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUser.class)
                    )
            ),
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = UpdateUser.class
                            )
                    )),

                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            },
            tags = "Пользователи"
    )
    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
        if (updateUser.getFirstName().isBlank()) {
            return ResponseEntity.ok(UpdateUser.builder().build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )},
            tags = "Пользователи"
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateUserImage(@RequestParam MultipartFile image) throws IOException {
        //  String url = String.valueOf(image.getResource());
        if (1 == 1) {
            ResponseEntity.ok().build();
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    /*@GetMapping("/image/download/{id}")
    public ResponseEntity< byte[] > getImage (@PathVariable Long id){
        Avatar avatar = avatarService.findAvatarByStudentId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }*/
}


