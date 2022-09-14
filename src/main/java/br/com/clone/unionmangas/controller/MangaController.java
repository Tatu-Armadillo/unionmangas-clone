package br.com.clone.unionmangas.controller;

import java.util.List;

import javax.transaction.Transactional;

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

import br.com.clone.unionmangas.dto.manga.MangaFindDto;
import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.response.ResponseBase;
import br.com.clone.unionmangas.response.ResponseBasePaginado;
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
    public ResponseEntity<ResponseBasePaginado<List<MangaFindDto>>> findAllMangas(
            @ApiIgnore @PageableDefault(sort = "mainTitle", direction = Direction.DESC) Pageable pageable,
            @ApiParam(name = "filter") @RequestParam(required = false, defaultValue = "") final String filter) {
        final var response = this.mangaService.findAllByName(filter, pageable);
        final var base = ResponseBasePaginado.of(response);
        return ResponseEntity.ok(base);
    }

    @GetMapping("/{idManga}")
    @ApiOperation("Endpoint responsible for searching the manga by id")
    public ResponseEntity<ResponseBase<Manga>> findById(@ApiParam(name = "idManga") @PathVariable final Long idManga) {
        final var response = this.mangaService.findById(idManga);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

    @GetMapping("/week")
    @ApiOperation("Endpoint responsible for returning the mangas updated in the week")
    public ResponseEntity<ResponseBasePaginado<List<MangaWeekDto>>> releaseWeek(
            @ApiIgnore @PageableDefault(sort = "lastUpdate", direction = Direction.DESC) Pageable pageable) {
        final var response = this.mangaService.releaseWeek(pageable);
        final var base = ResponseBasePaginado.of(response);
        return ResponseEntity.ok(base);
    }

    @PostMapping
    @Transactional
    @ApiOperation("Endpoint responsible for creating a new manga with author and genres")
    public ResponseEntity<ResponseBase<Manga>> createManga(@ApiParam(name = "manga") @RequestBody final Manga manga) {
        final var response = this.mangaService.create(manga);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

    @PutMapping("/{idManga}")
    @Transactional
    @ApiOperation("Endpoint responsible for changing the existing sleeve in the system")
    public ResponseEntity<ResponseBase<Manga>> updateManga(
            @ApiParam(name = "idManga") @PathVariable final Long idManga,
            @ApiParam(name = "manga") @RequestBody final Manga manga) {
        final var response = this.mangaService.update(idManga, manga);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

    @PutMapping("/image/{idManga}")
    @Transactional
    @ApiOperation("Endpoint responsible for inserting chapter cover in the system")
    public ResponseEntity<ResponseBase<String>> updateImageManga(
            @ApiParam(name = "idManga") @PathVariable final Long idManga,
            @ApiParam(name = "image") @RequestParam final MultipartFile image) {
        this.mangaService.updateCover(idManga, image);
        final var base = ResponseBase.of("ok");
        return ResponseEntity.ok(base);
    }

    @DeleteMapping("/{idManga}")
    @Transactional
    @ApiOperation("Endpoint responsible for deleting mango from the system")
    public ResponseEntity<ResponseBase<String>> delete(@ApiParam(name = "idManga") @PathVariable final Long idManga) {
        var response = this.mangaService.delete(idManga);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

}
