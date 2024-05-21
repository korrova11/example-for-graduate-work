package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Builder;
@Data
@Builder
public class Comment {

    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private Integer pk;
    private String text;

}
