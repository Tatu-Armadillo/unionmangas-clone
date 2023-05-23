package br.com.clone.unionmangas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.clone.unionmangas.dto.author.AuthorGetDto;
import br.com.clone.unionmangas.dto.author.AuthorParamDto;
import br.com.clone.unionmangas.response.*;
import br.com.clone.unionmangas.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/author")
@Tag(name = "Author", description = "Endpoints for Managing Authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Searching author", description = "responsible for searching author by name or pseudonym", tags = {
            "Author" }, responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AuthorGetDto.class))) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBasePaginado<List<AuthorGetDto>>> findByName(
           @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable,
            @RequestParam(required = true) final String name) {
        final var response = this.authorService.findByName(pageable, name);
        final var base = ResponseBasePaginado.of(response);
        return ResponseEntity.ok(base);
    }

    @GetMapping("/{idAuthor}")
    @Operation(summary = "Find author by ID", description = "responsible for searching author by ID", tags = {
            "Author" }, responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorGetDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<AuthorGetDto>> findById(
            @PathVariable final Long idAuthor) {
        final var response = this.authorService.findById(idAuthor);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Adds a new author", description = "Add a new Author by passing in a JSON representation of the Author", tags = {
            "Author" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorGetDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<AuthorGetDto>> createAuthor(
            @RequestBody final AuthorParamDto author) {
        final var response = this.authorService.create(author);
        final var base = ResponseBase.of(response);
        return ResponseEntity.ok(base);
    }

}
