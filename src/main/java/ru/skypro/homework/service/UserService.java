package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

public interface UserService {
    boolean changePassword(NewPassword changePassword);
    User getUserDto();

    boolean validationPassword(NewPassword newPassword);
}
