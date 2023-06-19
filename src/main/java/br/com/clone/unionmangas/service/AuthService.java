package br.com.clone.unionmangas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.config.security.TokenService;
import br.com.clone.unionmangas.dto.security.AccountCredentialsDto;
import br.com.clone.unionmangas.dto.security.CreateCredentialsDto;
import br.com.clone.unionmangas.dto.security.TokenDto;
import br.com.clone.unionmangas.model.User;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthService(
            final AuthenticationManager authenticationManager,
            final PasswordEncoder passwordEncoder,
            final UserService userService,
            final TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenDto signin(AccountCredentialsDto data) {
        final var username = data.getUserName();
        final var password = data.getPassword();
        final var user = this.userService.findByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("Invalid username/password");
        }

        final var authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final var token = new TokenDto(username, tokenService.createToken((User) authentication.getPrincipal()));

        return token;
    }

    public void create(CreateCredentialsDto data, boolean isScan) {
        final var password = passwordEncoder.encode(data.getPassword());
        data.setPassword(password);
        this.userService.create(data, isScan);
    }

}
