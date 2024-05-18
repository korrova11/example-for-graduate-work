package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean changePassword(NewPassword changePassword) {
        return true;
    }

    @Override
    public User getUserDto() {
        return null;
    }
    @Override
    public boolean validationPassword(NewPassword newPassword){
        int n=newPassword.getNewPassword().length();
        int m=newPassword.getCurrentPassword().length();
        if ((n<8||n>16)||(m<8||m>16)){return false;}
        else {return true;}
    }
}

