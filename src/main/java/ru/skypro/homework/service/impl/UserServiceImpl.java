package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl  {
    private final UserRepository repository;

    public boolean changePassword(NewPassword changePassword) {
        return true;
    }

    public UserEntity findById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public User getUserDto() {
        return null;
    }
    public UserEntity findByLogin(String login){
        return repository.findUserEntityByLogin(login);
    }
    public boolean adUser(Register register){
        UserEntity userEntity = UserMapper.INSTANCE.registerToUserEntity(register);
        if((repository.save(userEntity).getLogin()).equals(register.getUsername())) {
            return true;
        }
        else return false;

    }

    public boolean validationPassword(NewPassword newPassword){
        int n=newPassword.getNewPassword().length();
        int m=newPassword.getCurrentPassword().length();
        if ((n<8||n>16)||(m<8||m>16)){return false;}
        else {return true;}
    }
}

