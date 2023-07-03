package br.com.clone.unionmangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.config.response.ResponseBase;
import br.com.clone.unionmangas.config.response.ResponseBasePaginado;
import br.com.clone.unionmangas.dto.chapter.ChapterGetDto;
import br.com.clone.unionmangas.dto.chapter.ChapterParamDto;
import br.com.clone.unionmangas.model.Chapter;
import br.com.clone.unionmangas.service.ChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/chapters")
@Tag(name = "Chapter", description = "Endpoints for Managing Chapters")
public class ChapterController {

        private final ChapterService chapterService;

        @Autowired
        public ChapterController(ChapterService chapterService) {
                this.chapterService = chapterService;
        }

        @GetMapping("/{idManga}")
        @Operation(summary = "Pages inside the manga", description = "responsible for fetching the pages inside the manga", tags = {
                        "Chapter" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Chapter.class))) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBasePaginado<List<ChapterGetDto>>> findChaptersByManga(
                        @PageableDefault(sort = "releaseDate", direction = Direction.ASC) final Pageable pageable,
                        @PathVariable final Long idManga) {
                final var base = this.chapterService.findChaptersByManga(pageable, idManga);
                final var response = ResponseBasePaginado.of(base);
                return ResponseEntity.ok(response);
        }

        @GetMapping("read/{idManga}/online/{numberChapter}")
        @Operation(summary = "Opening chapter for reading", description = "responsible for opening chapter for reading", tags = {
                        "Chapter" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Chapter.class))) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<ChapterGetDto>> readChapter(
                        @PathVariable final Long idManga,
                        @PathVariable final Integer numberChapter) {
                final var base = this.chapterService.openChapter(idManga, numberChapter);
                final var response = ResponseBase.of(base);
                return ResponseEntity.ok(response);
        }

        @Transactional
        @PostMapping("/{idManga}")
        @SecurityRequirement(name = "bearer-key")
        @Operation(summary = "Add pages", description = "adding pages to manga", tags = {
                        "Chapter" }, responses = {
                                        @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Chapter.class))),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBase<Void>> insertChapters(
                        @PathVariable final Long idManga,
                        @RequestBody final ChapterParamDto chapterDto) {
                this.chapterService.insertChapters(idManga, chapterDto);
                return ResponseEntity.ok(ResponseBase.success());
        }

}
