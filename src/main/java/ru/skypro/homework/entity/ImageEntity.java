package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

//@Entity

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity


public  class ImageEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
   // private String url;

}
