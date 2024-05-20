package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;





@Data
@Builder
public class Image {

    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;


}
