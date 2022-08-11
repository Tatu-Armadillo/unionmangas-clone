package br.com.clone.unionmangas.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.service.MangaService;

@RestController
@RequestMapping("/mangas")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<List<Manga>> findAllMangas(
            @RequestParam(required = false, defaultValue = "") final String filter) {
        var response = this.mangaService.findAllByName(filter);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idManga}")
    public ResponseEntity<Manga> findById(@PathVariable final Long idManga) {
        var response = this.mangaService.findById(idManga);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Manga> createManga(@RequestBody final Manga manga) {
        var respose = this.mangaService.create(manga);
        return ResponseEntity.ok(respose);
    }

    @PutMapping("/{idManga}")
    @Transactional
    public ResponseEntity<Manga> updateManga(@PathVariable final Long idManga, @RequestBody final Manga manga) {
        var respose = this.mangaService.update(idManga, manga);
        return ResponseEntity.ok(respose);
    }

    @PutMapping("/image/{idManga}")
    @Transactional
    public ResponseEntity<?> updateImageManga(@PathVariable final Long idManga,
            @RequestParam final MultipartFile image) {
        this.mangaService.updateBlobImage(idManga, image);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{idManga}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable final Long idManga) {
        var response = this.mangaService.delete(idManga);
        return ResponseEntity.ok(response);
    }

}
