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
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "file_size")
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

}
