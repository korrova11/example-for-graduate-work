package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    @JsonIgnore
    private byte[] data;
   /* @OneToOne
    private SecurityProperties.User user;*/


}
