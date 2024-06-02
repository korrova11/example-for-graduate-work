package ru.skypro.homework.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

@Service
@Data
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserServiceImpl service;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = service.findByLogin(username).get();
        return new MyUserPrincipal(userEntity);
    }


}
