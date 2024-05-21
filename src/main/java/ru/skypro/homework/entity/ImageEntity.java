package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor

public class ImageEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

}