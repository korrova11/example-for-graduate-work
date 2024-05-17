package ru.skypro.homework.service;

import ru.skypro.homework.dto.ChangePassword;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    boolean changePassword(ChangePassword changePassword);
    UserDto getUserDto();

}
