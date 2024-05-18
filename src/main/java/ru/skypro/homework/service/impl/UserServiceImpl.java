package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean changePassword(NewPassword changePassword) {
        return true;
    }

    @Override
    public UserDto getUserDto() {
        return null;
    }
}
