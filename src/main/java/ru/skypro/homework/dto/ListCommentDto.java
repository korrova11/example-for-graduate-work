package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skypro.homework.entity.Comment;

import java.util.List;


@Data
public class ListCommentDto {
    private int count;
    private List<Comment> results;
}
