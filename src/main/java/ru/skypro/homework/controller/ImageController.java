package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageEntityRepository;

import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageEntityRepository repository;

    @GetMapping("/image/download/{id}")
    public ResponseEntity< byte[] > getImage (@PathVariable Long id){
        Optional<ImageEntity> imageEntity = repository.findById(id);
        if(imageEntity.isEmpty()) {
            return
                    ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imageEntity.get().getMediaType()));
        headers.setContentLength(imageEntity.get().getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageEntity.get().getData());
    }
}
