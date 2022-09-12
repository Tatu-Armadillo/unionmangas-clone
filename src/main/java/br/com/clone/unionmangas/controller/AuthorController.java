package br.com.clone.unionmangas.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.dto.author.AuthorGetDto;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @ApiOperation("Endpoint responsible for searching author by name or pseudonym")
    public ResponseEntity<Page<AuthorGetDto>> findByName(
            @ApiIgnore @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable,
            @ApiParam(name = "name") @RequestParam(required = true) final String name) {
        var response = this.authorService.findByName(pageable, name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idAuthor}")
    @ApiOperation("Endpoint responsible for searching the author by id")
    public ResponseEntity<AuthorGetDto> findById(@ApiParam(name = "idAuthor") @PathVariable final Long idAuthor) {
        var response = this.authorService.findById(idAuthor);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @ApiOperation("Endpoint responsible for creating an author")
    public ResponseEntity<Author> createAuthor(@ApiParam(name = "idAuthor") @RequestBody final Author author) {
        var response = this.authorService.create(author);
        return ResponseEntity.ok(response);
    }

}
