package ru.skypro.homework.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CommentEntity {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    @GeneratedValue
    @Id
    private Integer pk;
    private String text;
}
