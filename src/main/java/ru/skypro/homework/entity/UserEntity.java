package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.dto.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {
    @GeneratedValue
    @Id
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

}