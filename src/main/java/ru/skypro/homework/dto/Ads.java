package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.dto.Ad;

import java.util.List;

@Data
public class Ads {
    private Integer count;//=results.size();
    private List<Ad> results;

}
