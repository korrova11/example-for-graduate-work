package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.util.Optional;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentController {
    private final CommentServiceImpl commentService;

    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найдены комментарии"

                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NotFound"
                    )},
            tags = "Комментарии"
    )
    @GetMapping("{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
       Optional<Comments> comments = commentService.getAllByAd(id.longValue());
       if (comments.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       else
        return ResponseEntity.ok(comments.get());
    }

    @Operation(
            summary = "Добавление комментария к объявлению",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Comment.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий добавлен"

                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NotFound"
                    )},
            tags = "Комментарии"
    )
    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id,@RequestBody CreateOrUpdateComment comment,
                                              Authentication authentication) {
        if (comment.equals(comment)) {
            return ResponseEntity.ok().build();
        } // если не найден в БД вернуть 404, если не авторизирован вернуть 403.
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            },
            tags = "Комментарии")


    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId) { // добавить объявление
        // если объявление найдено и коммент найден, ОК
        // если не авторизирован, не ок
        // прописать ошибки Forbidden и Not found
        commentService.deleteComment(adId,commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",

                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            },
            tags = "Комментарии")
    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId,
                                                 @PathVariable Integer commentId,
                                                 @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        // если объявление найдено и коммент найден, ОК
        // если не авторизирован, не ок
        // прописать ошибки Forbidden и Not found
        return ResponseEntity.ok().build();
    }
}
