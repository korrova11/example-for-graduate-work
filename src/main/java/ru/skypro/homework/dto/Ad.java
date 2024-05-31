package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Ad {
    private Integer pk;
    private Integer author;
    private String image;
    private Integer price;
    private String title;
}
