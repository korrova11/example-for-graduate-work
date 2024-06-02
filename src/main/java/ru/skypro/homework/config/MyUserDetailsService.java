package ru.skypro.homework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
@Service
@Data
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findUserEntityByLogin(username);
        return new MyUserPrincipal(userEntity);
    }


}
