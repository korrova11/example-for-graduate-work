package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final PasswordEncoder encoder;
    private final UserServiceImpl userService;
    private final UserRepository repository;
    private final MyUserDetailsService myUserDetailsService;



    @Override
    public boolean login(String userName, String password) {

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
        if (!encoder.matches(password, userDetails.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean  register(Register register) {

        logger.info("Was invoked method for register");
        UserEntity userEntity = UserMapper.INSTANCE.registerToUserEntity(register);

        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        repository.save(userEntity);
        return true;
    }
    public boolean changePassword(String login,NewPassword newPassword){
        Optional<UserEntity> user = userService.findByLogin(login);
        if (user.isEmpty()) return false;
        if (!encoder.matches(newPassword.getCurrentPassword(), user.get().getPassword())) {
            return false;
        }
        user.get().setPassword(encoder.encode(newPassword.getNewPassword()));
        repository.save(user.get());
        return true;

    }


}
