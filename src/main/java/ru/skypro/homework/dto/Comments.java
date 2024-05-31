package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class Comments {
    private int count;
    private List<Comment> results;
}
