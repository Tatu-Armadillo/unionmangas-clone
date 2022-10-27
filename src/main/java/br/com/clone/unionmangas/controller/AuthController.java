package br.com.clone.unionmangas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clone.unionmangas.dto.security.AccountCredentialsDto;
import br.com.clone.unionmangas.dto.security.CreateCredentialsDto;
import br.com.clone.unionmangas.dto.security.TokenDto;
import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.response.ResponseBase;
import br.com.clone.unionmangas.service.AuthService;
import br.com.clone.unionmangas.service.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Endpoints for Managing token")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private static final String message = "Invalid client request!";

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Operation(summary = "Create a new users", tags = { "Authentication" })
    @PostMapping("/create")
    public ResponseEntity<ResponseBase<Void>> create(
        @ApiParam(name = "data") @RequestBody final CreateCredentialsDto data,
        @ApiParam(name = "isScan") @RequestParam final boolean isScan
        ) {
        if (data == null
                || data.getEmail() == null || data.getEmail().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank()) {
            throw new NegocioException(message);
        }
        this.userService.create(data, isScan);
        return ResponseEntity.ok(ResponseBase.success());
    }

    @Operation(summary = "Authenticates a user and returns a token", tags = { "Authentication" })
    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signin(@RequestBody AccountCredentialsDto data) {
        if (data == null
                || data.getUserName() == null || data.getUserName().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank()) {
            throw new NegocioException(message);
        }

        final var token = this.authService.signin(data);
        if (token == null) {
            throw new NegocioException(message);
        }
        return token;
    }

    @Operation(summary = "Refresh token for authenticated user and returns a token", tags = { "Authentication" })
    @PutMapping("/refresh/{username}")
    public ResponseEntity<TokenDto> refreshToken(
            @PathVariable("username") String username,
            @RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank()) {
            throw new NegocioException(message);
        }
        final var token = this.authService.refreshToken(username, refreshToken);
        if (token == null) {
            throw new NegocioException(message);
        }
        return token;
    }

}
