package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdServiceImpl adService;


    @Operation(
            tags = "Объявления",
            summary = "Добавление обьявления",

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
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(
                            implementation = Ad.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAd(@RequestPart(name = "image") MultipartFile image,
                                   @RequestPart(name = "properties") CreateOrUpdateAd properties,
                                   Authentication authentication) throws IOException {
        return new ResponseEntity<>(adService.addAd(image, properties, authentication), HttpStatus.CREATED);

    }

    @Operation(
            tags = "Объявления",
            summary = "Получение всех объявлений",

            responses = {@ApiResponse(
                    responseCode = "200", description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ads.class)
                    )
            ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized")
            }
    )

    @GetMapping()
    public ResponseEntity<?> getAllAds() {
        return ResponseEntity.ok(adService.getAll());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable Integer id, Authentication authentication) {
        if (adService.isMainOrAdmin(id, authentication)) {
            if (adService.deleteAd(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

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
                            implementation = ExtendedAd.class
                    )

            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable Integer id) {
        ExtendedAd ad = adService.getById(id);
        if (ad == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return ResponseEntity.ok(ad);

    }

    @Operation(
            tags = "Объявления",
            summary = "Обновление информации об объявлении",
            operationId = "updateAds",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    schema = @Schema(
                            implementation = Ad.class
                    )

            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Ad> updateAds(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                        Authentication authentication) {
        if (!adService.isMainOrAdmin(id, authentication))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Optional<Ad> ad = adService.changeAd(id, createOrUpdateAd, authentication);
        if (ad.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else return ResponseEntity.ok(ad.get());


    }

    @Operation(
            tags = "Объявления",
            summary = "Получение объявлений авторизованного пользователя",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {@Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            encoding = @Encoding(
                                    name = "properties",
                                    contentType = "application/json"
                            ))
                    }
            ),
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    schema = @Schema(
                            implementation = Ads.class
                    )
            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsByUser(authentication));
    }

    @Operation(
            tags = "Объявления",
            summary = "Обновление картинки объявления",
            operationId = "updateImage",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(
                                    type = "string",
                                    format = "byte"
                            )
                    )

            )),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable Integer id,
                                         @RequestBody MultipartFile image,
                                         Authentication authentication) throws IOException {
        if (adService.isMainOrAdmin(id, authentication)) {
            adService.changeImageAd(id.longValue(), image);
            return ResponseEntity.ok(Base64.encodeBase64URLSafeString(image.getBytes()));
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

}
