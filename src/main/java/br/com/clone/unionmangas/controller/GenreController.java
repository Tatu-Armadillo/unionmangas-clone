package br.com.clone.unionmangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.model.Genre;
import br.com.clone.unionmangas.response.ResponseBasePaginado;
import br.com.clone.unionmangas.service.GenreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

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
    public ResponseEntity<ResponseBasePaginado<List<Genre>>> getAllGenres(
            @ApiIgnore @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable, 
            @ApiParam(name = "filter") @RequestParam(required = false, defaultValue = "") final String name) {
        final var base = this.genreService.findAllByName(pageable, name);
        final var response = ResponseBasePaginado.of(base);
        return ResponseEntity.ok(response);
    }

}
