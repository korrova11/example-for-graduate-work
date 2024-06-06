package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserEntityByUserName(String userName);
    Optional<UserEntity> findByLogin(String login);
}
