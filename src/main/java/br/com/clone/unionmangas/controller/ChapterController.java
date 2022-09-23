package br.com.clone.unionmangas.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.model.Chapter;
import br.com.clone.unionmangas.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiOperation("Endpoint responsible for fetching the pages inside the manga")
    @Operation(summary = "Pages inside the manga", description = "responsible for fetching the pages inside the manga", tags = {
            "Chapter" }, responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Chapter.class))) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Chapter>> findChaptersByManga(
            @ApiParam(name = "idManga") @PathVariable Long idManga) {
        final var response = this.chapterService.findChaptersByManga(idManga);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/{idManga}")
    @ApiOperation("Endpoint responsible for adding pages to manga")
    @Operation(summary = "Add pages", description = "adding pages to manga", tags = {
            "Chapter" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Chapter.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<Chapter>> insertChapters(
            @ApiParam(name = "idManga") @PathVariable final Long idManga,
            @ApiParam(name = "chapter") @RequestBody final Chapter chapter) {
        final var response = this.chapterService.insertChapters(idManga, chapter);
        return ResponseEntity.ok(response);
    }

}
