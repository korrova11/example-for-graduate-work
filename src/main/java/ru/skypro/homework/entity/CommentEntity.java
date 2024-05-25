package ru.skypro.homework.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Date createdAt;

    @Column
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntity_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    private AdsEntity ads;

}
