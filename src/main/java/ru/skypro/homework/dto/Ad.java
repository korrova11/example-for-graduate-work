package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    private String pk;
    private Integer author;
    private String image;
    private Integer price;
    private String title;
}
