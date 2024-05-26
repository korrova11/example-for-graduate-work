package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ads")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Long price;
    @Column(name = "title")
    private String title;
    @Column(name = "descriptions")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "ads", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

}
