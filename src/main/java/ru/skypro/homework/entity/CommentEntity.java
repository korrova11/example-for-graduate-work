package ru.skypro.homework.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "comment")
@ToString
public class CommentEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Date createdAt;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntity_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "ads_id",nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AdEntity ads;

}
