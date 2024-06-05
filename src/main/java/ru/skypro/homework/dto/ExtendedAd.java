package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Pattern;

@Builder
@Data
public class ExtendedAd {
    private Integer pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    @Pattern(regexp = ("\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}"))
    private String phone;
    private Integer price;
    private String title;
}
