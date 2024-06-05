package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateComment {

    @Schema(description = "текст комментария")
    @NotBlank(message = "Комментарий не может быть пуст")
    @Size(min = 8, message = "размер комментария минимум 8 символов")
    @Size(max = 64, message = "размер комментария максимум 64 символа")
    private String text;
}
