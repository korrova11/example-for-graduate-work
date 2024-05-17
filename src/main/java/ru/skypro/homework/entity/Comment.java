package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @GeneratedValue
    @Id
    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private String text;

}
