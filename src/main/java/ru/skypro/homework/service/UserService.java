package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    boolean changePassword(NewPassword changePassword);
    UserDto getUserDto();

}
