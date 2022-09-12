package br.com.clone.unionmangas.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.clone.unionmangas.dto.author.AuthorGetDto;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.response.*;
import br.com.clone.unionmangas.service.AuthorService;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @ApiOperation("Endpoint responsible for searching author by name or pseudonym")
    public ResponseEntity<ResponseBasePaginado<List<AuthorGetDto>>> findByName(
            @ApiIgnore @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable,
            @ApiParam(name = "name") @RequestParam(required = true) final String name) {
        final var response = this.authorService.findByName(pageable, name);
        final var base = ResponseBasePaginado.of(response);
        return ResponseEntity.ok(base);
    }

    @GetMapping("/{idAuthor}")
    @ApiOperation("Endpoint responsible for searching the author by id")
    public ResponseEntity<ResponseBase<AuthorGetDto>> findById(@ApiParam(name = "idAuthor") @PathVariable final Long idAuthor) {
        final var response = this.authorService.findById(idAuthor);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

    @PostMapping
    @Transactional
    @ApiOperation("Endpoint responsible for creating an author")
    public ResponseEntity<ResponseBase<Author>> createAuthor(@ApiParam(name = "idAuthor") @RequestBody final Author author) {
        final var response = this.authorService.create(author);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

}
