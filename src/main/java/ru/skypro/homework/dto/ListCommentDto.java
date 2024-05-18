package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import ru.skypro.homework.entity.Comment;

import java.util.List;
@AllArgsConstructor
public class ListCommentDto {
    private List<Comment> results;
    private int count=results.size();
}
