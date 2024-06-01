package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "users")
public class UserEntity {
    @GeneratedValue
    @Id
    private Long id;
    @Column( unique = true, nullable = false)
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JoinColumn(referencedColumnName = "id", name = "image_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ImageEntity imageEntity;

}
