package ru.skypro.homework.dto;

import javax.validation.constraints.Size;

public class CreateOrUpdateComment {
    @Size(min = 8, max=64)
    private String text;
}
