package br.com.clone.unionmangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.model.Genre;
import br.com.clone.unionmangas.service.GenreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;
    
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @ApiOperation("Endpoint responsible for showing existing genres in the system")
    public ResponseEntity<List<Genre>> getAllGenres(
            @ApiParam(name = "filter") @RequestParam(required = false, defaultValue = "") final String name) {
        final var response = this.genreService.findAllByName(name);
        return ResponseEntity.ok(response);
    }

}
