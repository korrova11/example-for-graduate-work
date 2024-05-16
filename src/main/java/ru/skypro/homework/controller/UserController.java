package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ChangePassword;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Обновление пароля",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Существующий пароль, новый пароль",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ChangePassword.class)
                    )
            ),
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Пароль обновлен"


            ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователен не авторизован"


                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Пароль не соответствует шаблону"


                    )},
            tags = "Пользователи"
    )


    @PostMapping("/set_password")
    public ResponseEntity<?> login(@RequestBody ChangePassword changePassword) {
        if (changePassword.equals("")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else if (userService.changePassword(changePassword)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Найденный пользователь",

                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)
                    )
            ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователен не авторизован"


                    )},
            tags = "Пользователи"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserDto() {
        if (1 == 1) {
            return ResponseEntity.ok(new UserDto());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
