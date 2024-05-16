package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
public class ChangePassword {

    private String currentPassword;

    private String newPassword;

}
