package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
@Data
@Builder
@AllArgsConstructor
public class CreateOrUpdateComment {
    @Size(min = 8, max=64)
    private String text;
}
