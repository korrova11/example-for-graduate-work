package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
@Table(name = "users")
public class UserEntity {
    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true, nullable = true)
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    @JoinColumn(referencedColumnName = "id", name = "image_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ImageEntity imageEntity;


}
