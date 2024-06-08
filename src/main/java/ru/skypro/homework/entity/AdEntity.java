package ru.skypro.homework.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ads")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Integer price;
    @Column(name = "title")
    private String title;
    @Column(name = "descriptions")
    private String description;
    @JoinColumn(referencedColumnName = "id", name = "image_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ImageEntity imageEntity;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @OneToMany(mappedBy = "ads", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

}
