package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema
public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    @Pattern(regexp = ("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}"))
    private String phone;
    private Role role;
    @Schema( type = "string", format = "binary")
    private Image image;
}
