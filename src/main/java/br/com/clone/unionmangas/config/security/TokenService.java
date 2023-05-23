package br.com.clone.unionmangas.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.User;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(User user) {
        try {
            final var algorithm = Algorithm.HMAC256(secret);
            final String token = JWT.create()
                    .withIssuer("Web Club Travel")
                    .withSubject(user.getUsername())
                    .withExpiresAt(this.expirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new NegocioException("Error to generate Token JWT");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            final var algorithm = Algorithm.HMAC256(secret);
            final var verifier = JWT.require(algorithm)
                    .withIssuer("Web Club Travel")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

            return verifier;
        } catch (Exception e) {
            throw new NegocioException("Token JWT Invalid or expired");
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(10)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
