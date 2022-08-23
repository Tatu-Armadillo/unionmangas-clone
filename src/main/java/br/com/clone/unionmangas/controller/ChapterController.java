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

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/{idManga}")
    @ApiOperation("Endpoint responsible for fetching the pages inside the manga")
    public ResponseEntity<List<Chapter>> findChaptersByManga(
            @ApiParam(name = "idManga") @PathVariable Long idManga) {
        final var response = this.chapterService.findChaptersByManga(idManga);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping("/{idManga}")
    @ApiOperation("Endpoint responsible for adding pages to manga")
    public ResponseEntity<List<Chapter>> insertChapters(
        @ApiParam(name = "idManga") @PathVariable final Long idManga,
        @ApiParam(name = "chapter") @RequestBody final Chapter chapter) {
        final var response = this.chapterService.insertChapters(idManga, chapter);
        return ResponseEntity.ok(response);
    }

}
