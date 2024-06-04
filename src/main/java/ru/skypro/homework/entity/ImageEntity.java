package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity
@ToString
public class ImageEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

}
