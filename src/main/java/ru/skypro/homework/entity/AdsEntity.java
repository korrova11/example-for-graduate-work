package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Image;
import ru.skypro.homework.dto.User;

import javax.persistence.*;

@Entity
@Table(name = "Ads")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //делегирует установку ID на уровень базы данных(для бд PRIMARY KEY, AUTOINCREMENT)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Long price;
    @Column(name = "title")
    private String title;
    @Column(name = "descriptions")
    private String descriptions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;



}
