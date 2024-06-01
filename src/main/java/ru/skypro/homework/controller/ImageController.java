package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
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

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor

public class ImageController {
    private final ImageEntityRepository repository;

    @GetMapping("/image/download/{id}")
    public ResponseEntity< byte[] > getImage (@PathVariable Long id){
        ImageEntity imageEntity = repository.findById(id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imageEntity.getMediaType()));
        headers.setContentLength(imageEntity.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(imageEntity.getData());
    }
}
