package br.com.clone.unionmangas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.config.response.ResponseBase;
import br.com.clone.unionmangas.dto.security.AccountCredentialsDto;
import br.com.clone.unionmangas.dto.security.CreateCredentialsDto;
import br.com.clone.unionmangas.dto.security.TokenDto;
import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Endpoints for Managing token")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private static final String message = "Invalid client request!";

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Create a new users", tags = { "Authentication" })
    @PostMapping("/create")
    public ResponseEntity<ResponseBase<Void>> create(
            @RequestBody final CreateCredentialsDto data,
            @RequestParam final boolean isScan) {
        if (data == null
                || data.getEmail() == null || data.getEmail().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank()) {
            throw new NegocioException(message);
        }
        this.authService.create(data, isScan);
        return ResponseEntity.ok(ResponseBase.success());
    }

    @Operation(summary = "Authenticates a user and returns a token", tags = { "Authentication" })
    @PostMapping("/signin")
    public ResponseEntity<ResponseBase<TokenDto>> signin(@RequestBody AccountCredentialsDto data) {
        if (data == null
                || data.getUserName() == null || data.getUserName().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank()) {
            throw new NegocioException(message);
        }

        final var token = this.authService.signin(data);
        final var base = ResponseBase.of(token);
        return ResponseEntity.ok(base);
    }

}
