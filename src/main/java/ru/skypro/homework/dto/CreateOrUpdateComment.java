package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
@Data
@Builder
public class CreateOrUpdateComment {
    @Size(min = 8, max=64)
    private String text;
}
