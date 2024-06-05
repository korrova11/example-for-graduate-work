package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "comment")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Date createdAt;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userEntity_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ads_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AdEntity ads;

}
