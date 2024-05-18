package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.Ad;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAd;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {
    private final AdService adService;
//    private final Comment comment;

    @Operation(
            tags = "Объявления",
            summary = "Добавление обьявления",
            operationId = "addAd",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            encoding = @Encoding(
                                    name = "properties",
                                    contentType = "application/json"
                            ))
                    }
            ),
            responses = {@ApiResponse(responseCode = "201", description = "Created", content = @Content(
                    schema = @Schema(
                            implementation = Ad.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAd(@RequestPart(name = "image") MultipartFile image,
                                   @RequestPart(name = "properties") CreateOrUpdateAd properties) {
        Ad ad = adService.addAd(image, properties);
        return ResponseEntity.ok(ad);
    }

    @Operation(
            tags = "Объявления",
            summary = "Получение всех объявлений",
            operationId = "getAllAds",
            responses = {@ApiResponse(
                    responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ad.class),
                            examples = @ExampleObject
                                    ("{\n" +
                                            " \"count\": 0,\n" +
                                            " \results\": [ \n" +
                                            "{\n" +
                                            "  \"author\": 0,\n" +
                                            "  \"image\": \"string\",\n" +
                                            "  \"pk\": 0,\n" +
                                            "  \"price\": 0,\",\n" +
                                            "  \"title\": \"string\",\n" +
                                            "}" +
                                            "] \n" +
                                            "}"
                                    )
                    )

            ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized")
            }
    )

    @GetMapping()
    public ResponseEntity<?> getAllAds() {
        return null;
    }

    @Operation(
            tags = "Объявления",
            summary = "Удаление объявления",
            operationId = "removeAd",
            responses = {
                    @ApiResponse(
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = Ad.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204", description = "No Content"),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "403", description = "Forbidden"),
                    @ApiResponse(
                            responseCode = "404", description = "Not found")
            }
    )

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Объявления",
            summary = "Получение информации об объявлении",
            operationId = "updateAds",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            encoding = @Encoding(
                                    name = "properties",
                                    contentType = "application/json"
                            ))
                    }
            ),
            responses = {@ApiResponse(responseCode = "201", description = "Created", content = @Content(
                    schema = @Schema(
                            implementation = Ad.class
                    ),

                    examples = @ExampleObject
                            ("{\n" +
                                    " \"count\": 0,\n" +
                                    " \results\": [ \n" +
                                    "{\n" +
                                    "  \"author\": 0,\n" +
                                    "  \"image\": \"string\",\n" +
                                    "  \"pk\": 0,\n" +
                                    "  \"price\": 0,\",\n" +
                                    "  \"title\": \"string\",\n" +
                                    "}" +
                                    "] \n" +
                                    "}"
                            )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id) {
        return null;
    }

    @Operation(
            tags = "Объявления",
            summary = "Обновление информации об объявлении",
            operationId = "updateAds",
            responses = {@ApiResponse(
                    responseCode = "200", description = "OK"
            ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized"
                    )}
    )
    @PatchMapping("{id}")
    public ResponseEntity<?> updateAds(@RequestBody Ads ads) {
        return null;
    }

    @Operation(
            tags = "Объявления",
            summary = "Получение объявлений авторизованного пользователя",
            operationId = "getAdsMe",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            encoding = @Encoding(
                                    name = "properties",
                                    contentType = "application/json"
                            ))
                    }
            ),
            responses = {@ApiResponse(responseCode = "201", description = "Created", content = @Content(
                    schema = @Schema(
                            implementation = Ad.class
                    ),

                    examples = @ExampleObject
                            ("{\n" +
                                    " \"count\": 0,\n" +
                                    " \results\": [ \n" +
                                    "{\n" +
                                    "  \"author\": 0,\n" +
                                    "  \"image\": \"string\",\n" +
                                    "  \"pk\": 0,\n" +
                                    "  \"price\": 0,\",\n" +
                                    "  \"title\": \"string\",\n" +
                                    "}" +
                                    "] \n" +
                                    "}"
                            )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(@RequestBody Ads ads) {
        return null;
    }

    @Operation(
            tags = "Объявления",
            summary = "Обновление картинки объявления",
            operationId = "updateImage",
            responses = {@ApiResponse(
                    responseCode = "200", description = "OK"
            ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized"
                    )}
    )
    @PatchMapping("/{id}/image")
    public ResponseEntity<?> updateImage(@PathVariable Integer id, @RequestBody MultipartFile image) {
        return null;
    }


}
