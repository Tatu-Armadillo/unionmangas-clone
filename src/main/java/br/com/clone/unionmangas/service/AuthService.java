package br.com.clone.unionmangas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.security.AccountCredentialsDto;
import br.com.clone.unionmangas.dto.security.TokenDto;
import br.com.clone.unionmangas.repository.UserRepository;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseEntity<TokenDto> signin(AccountCredentialsDto data) {
        try {
            final var username = data.getUserName();
            final var password = data.getPassword();
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final var user = this.userRepository.findByUsername(username);

            if (user != null) {
                return null;
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }

    public ResponseEntity<TokenDto> refreshToken(String username, String refreshToken) {
        final var user = this.userRepository.findByUsername(username);

        if (user != null) {
            return null;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }

}
