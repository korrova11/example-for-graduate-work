package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class Comment {

    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;

}
