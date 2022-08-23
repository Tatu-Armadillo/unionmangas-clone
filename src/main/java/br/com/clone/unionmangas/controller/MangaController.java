package br.com.clone.unionmangas.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.clone.unionmangas.dto.manga.MangaFindDto;
import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.service.MangaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/mangas")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping
    @ApiOperation("endpoint responsible for fetching mangos by main and alias name")
    public ResponseEntity<Page<MangaFindDto>> findAllMangas(
            @ApiIgnore @PageableDefault(sort = "mainTitle", direction = Direction.DESC) Pageable pageable,
            @ApiParam(name = "filter") @RequestParam(required = false, defaultValue = "") final String filter) {
        var response = this.mangaService.findAllByName(filter, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idManga}")
    @ApiOperation("Endpoint responsible for searching the manga by id")
    public ResponseEntity<Manga> findById(@ApiParam(name = "idManga") @PathVariable final Long idManga) {
        var response = this.mangaService.findById(idManga);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/week")
    @ApiOperation("Endpoint responsible for returning the mangas updated in the week")
    public ResponseEntity<Page<MangaWeekDto>> releaseWeek(
            @ApiIgnore @PageableDefault(sort = "lastUpdate", direction = Direction.DESC) Pageable pageable) {
        var response = this.mangaService.releaseWeek(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @ApiOperation("Endpoint responsible for creating a new manga with author and genres")
    public ResponseEntity<Manga> createManga(@ApiParam(name = "manga") @RequestBody final Manga manga) {
        var respose = this.mangaService.create(manga);
        return ResponseEntity.ok(respose);
    }

    @PutMapping("/{idManga}")
    @Transactional
    @ApiOperation("Endpoint responsible for changing the existing sleeve in the system")
    public ResponseEntity<Manga> updateManga(
            @ApiParam(name = "idManga") @PathVariable final Long idManga,
            @ApiParam(name = "manga") @RequestBody final Manga manga) {
        var respose = this.mangaService.update(idManga, manga);
        return ResponseEntity.ok(respose);
    }

    @PutMapping("/image/{idManga}")
    @Transactional
    @ApiOperation("Endpoint responsible for inserting chapter cover in the system")
    public ResponseEntity<?> updateImageManga(
            @ApiParam(name = "idManga") @PathVariable final Long idManga,
            @ApiParam(name = "image") @RequestParam final MultipartFile image) {
        this.mangaService.updateCover(idManga, image);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{idManga}")
    @Transactional
    @ApiOperation("Endpoint responsible for deleting mango from the system")
    public ResponseEntity<?> delete(@ApiParam(name = "idManga") @PathVariable final Long idManga) {
        var response = this.mangaService.delete(idManga);
        return ResponseEntity.ok(response);
    }

}
