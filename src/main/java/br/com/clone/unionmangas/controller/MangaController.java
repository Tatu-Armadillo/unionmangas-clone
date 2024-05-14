package br.com.clone.unionmangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.clone.unionmangas.config.response.ResponseBase;
import br.com.clone.unionmangas.config.response.ResponseBasePaginado;
import br.com.clone.unionmangas.dto.manga.MangaFindDto;
import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.service.MangaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/mangas")
@Tag(name = "Mangas", description = "Endpoints for Managing Mangas")
public class MangaController {

        private final MangaService mangaService;

        @Autowired
        public MangaController(MangaService mangaService) {
                this.mangaService = mangaService;
        }

        @GetMapping
        @Operation(summary = "Searching mangas", description = "responsible for searching mangas by primary or secondary name", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MangaFindDto.class))) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBasePaginado<List<MangaFindDto>>> findAllMangas(
                        @PageableDefault(sort = "mainTitle", direction = Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") final String filter) {
                final var response = this.mangaService.findAllByName(filter, pageable);
                final var base = ResponseBasePaginado.of(response);
                return ResponseEntity.ok(base);
        }

        // TODO NOVO METODO 
        // TODO FAZER A BUSCA PELO NOME DO AUTOR OU ID DO AUTOR 
        // @GetMapping
        // @Operation(summary = "Searching mangas by author", description = "responsible for browse works written by the selected author", tags = {
        //                 "Mangas" }, responses = {
        //                                 @ApiResponse(description = "Success", responseCode = "200", content = {
        //                                                 @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MangaFindDto.class))) }),
        //                                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
        //                                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        //                                 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        //                 })
        // public ResponseEntity<ResponseBasePaginado<List<MangaFindDto>>> findAllMangasByAuthor(
        //                 @PageableDefault(sort = "mainTitle", direction = Direction.DESC) Pageable pageable,
        //                 @RequestParam(required = false, defaultValue = "") final String filter) {
        //         final var response = this.mangaService.findAllMangasByAuthor(filter, pageable);
        //         final var base = ResponseBasePaginado.of(response);
        //         return ResponseEntity.ok(base);
        // }

        @GetMapping("/{idManga}")
        @Operation(summary = "Find manga by ID", description = "endpoint responsible for fetching mangas by main and alias name", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Manga.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<Manga>> findById(@PathVariable final Long idManga) {
                final var response = this.mangaService.findById(idManga);
                final var base = ResponseBase.of(response);
                return ResponseEntity.ok(base);
        }

        @GetMapping("/week")
        @Operation(summary = "Mangas Week", description = "responsible for returning the mangas updated in the week", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MangaWeekDto.class))) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBasePaginado<List<MangaWeekDto>>> releaseWeek(
                        @PageableDefault(sort = "lastUpdate", direction = Direction.DESC) Pageable pageable) {
                final var response = this.mangaService.releaseWeek(pageable);
                final var base = ResponseBasePaginado.of(response);
                return ResponseEntity.ok(base);
        }

        @PostMapping
        @Transactional
        @SecurityRequirement(name = "bearer-key")
        @Operation(summary = "Adds a new manga", description = "Add a new manga with author and categories", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Manga.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<Manga>> createManga(
                        @RequestBody final Manga manga) {
                final var response = this.mangaService.create(manga);
                final var base = ResponseBase.of(response);
                return ResponseEntity.ok(base);
        }

        @PutMapping("/{idManga}")
        @Transactional
        @SecurityRequirement(name = "bearer-key")
        @Operation(summary = "Udpdate a manga", description = "responsible for changing the existing sleeve in the system", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Manga.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<Manga>> updateManga(
                        @PathVariable final Long idManga,
                        @RequestBody final Manga manga) {
                final var response = this.mangaService.update(idManga, manga);
                final var base = ResponseBase.of(response);
                return ResponseEntity.ok(base);
        }

        @PutMapping("/image/{idManga}")
        @Transactional
        @SecurityRequirement(name = "bearer-key")
        @Operation(summary = "Udpdate a chapter cove", description = "responsible for inserting chapter cover in the system", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Manga.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<String>> updateImageManga(
                        @PathVariable final Long idManga,
                        @RequestParam final MultipartFile image) {
                this.mangaService.updateCover(idManga, image);
                final var base = ResponseBase.of("ok");
                return ResponseEntity.ok(base);
        }

        @DeleteMapping("/{idManga}")
        @Transactional
        @SecurityRequirement(name = "bearer-key")
        @Operation(summary = "Udpdate a chapter cove", description = "responsible for deleting manga from the system", tags = {
                        "Mangas" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Manga.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<String>> delete(
                        @PathVariable final Long idManga) {
                var response = this.mangaService.delete(idManga);
                final var base = ResponseBase.of(response);
                return ResponseEntity.ok(base);
        }

}
