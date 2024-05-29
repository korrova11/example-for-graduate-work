package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl  {
    private final UserRepository repositiry;

    public boolean changePassword(NewPassword changePassword) {
        return true;
    }

    public UserEntity findById(Long id){
        return repositiry.findById(id).orElseThrow();
    }
    public User getUserDto() {
        return null;
    }

    public boolean validationPassword(NewPassword newPassword){
        int n=newPassword.getNewPassword().length();
        int m=newPassword.getCurrentPassword().length();
        if ((n<8||n>16)||(m<8||m>16)){return false;}
        else {return true;}
    }
}

