package ru.skypro.homework.controller;

public class ImageController {
    /*@GetMapping("/image/download/{id}")
    public ResponseEntity< byte[] > getImage (@PathVariable Long id){
        Avatar avatar = avatarService.findAvatarByStudentId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }*/
}
