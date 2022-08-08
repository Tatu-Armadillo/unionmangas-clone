package br.com.clone.unionmangas.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Author> findByName(@RequestParam(required = true) final String name) {
        var response = this.authorService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idAuthor}")
    public ResponseEntity<Author> findById(@PathVariable final Long idAuthor) {
        var response = this.authorService.findById(idAuthor);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Author> createAuthor(@RequestBody final Author author) {
        var response = this.authorService.create(author);
        return ResponseEntity.ok(response);
    }

}
