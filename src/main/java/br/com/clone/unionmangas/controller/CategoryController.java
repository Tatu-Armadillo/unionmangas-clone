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

import br.com.clone.unionmangas.config.response.ResponseBasePaginado;
import br.com.clone.unionmangas.model.Category;
import br.com.clone.unionmangas.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoires")
@Tag(name = "Category", description = "Endpoints for Managing Categoires")
public class CategoryController {

        private final CategoryService categoryService;

        @Autowired
        public CategoryController(CategoryService categoryService) {
                this.categoryService = categoryService;
        }

        @GetMapping
        @Operation(summary = "Existing Categoires", description = "responsible for showing existing categoires in the system", tags = {
                        "Category" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class))) }),
                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<ResponseBasePaginado<List<Category>>> getAllCategoires(
                        @PageableDefault(sort = "name", direction = Direction.ASC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") final String name) {
                final var base = this.categoryService.findAllByName(pageable, name);
                final var response = ResponseBasePaginado.of(base);
                return ResponseEntity.ok(response);
        }

}
