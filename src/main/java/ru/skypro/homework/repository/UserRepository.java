package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByLogin(String login);
}
