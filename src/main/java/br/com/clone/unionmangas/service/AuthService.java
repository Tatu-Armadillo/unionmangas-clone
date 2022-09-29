package br.com.clone.unionmangas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.security.AccountCredentialsDto;
import br.com.clone.unionmangas.dto.security.TokenDto;
import br.com.clone.unionmangas.model.Permission;
import br.com.clone.unionmangas.model.User;
import br.com.clone.unionmangas.repository.PermissionRepository;
import br.com.clone.unionmangas.repository.UserRepository;
import br.com.clone.unionmangas.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            UserRepository userRepository,
            PermissionRepository permissionRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    public void create(AccountCredentialsDto data) {
        var permissions = this.permissionRepository.findAll();
        if (permissions.isEmpty()) {
            var roles = List.of(new Permission("ADMIN"),
                    new Permission("MANAGER"),
                    new Permission("COMMON_USER"),
                    new Permission("READER"));
            this.permissionRepository.saveAll(roles);
            this.permissionRepository.flush();
            permissions = this.permissionRepository.findAll();
        }

        final var user = new User(
                data.getUserName(),
                data.getUserName(),
                securityPassword(data.getPassword()),
                true,
                true,
                true,
                true);
        user.setPermissions(permissions);

        this.userRepository.save(user);
    }

    public ResponseEntity<TokenDto> signin(AccountCredentialsDto data) {
        try {
            final var username = data.getUserName();
            final var password = data.getPassword();
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final var user = this.userRepository.findByUsername(username);

            if (user != null) {
                final var tokenResponse = this.tokenProvider.createAccessToken(username, user.getRoles());
                return ResponseEntity.ok(tokenResponse);
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
            final var tokenResponse = this.tokenProvider.refreshToken(refreshToken);
            return ResponseEntity.ok(tokenResponse);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }

    private String securityPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

        String result = passwordEncoder.encode(password);
        return result.substring("{pbkdf2}".length());
    }

}
