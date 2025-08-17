package com.alura.Foro.Hub.challenge.infra.security;

import com.alura.Foro.Hub.challenge.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secreto}")
    private String secreto;

    public String generarToken(Usuario usuario){

        try {
            var algoritmo = Algorithm.HMAC256(secreto);
            return JWT.create()
                    .withIssuer("API FORO HUB")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaDeExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }

    }

    private Instant fechaDeExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }

    public String getSubject(String tokenJWT){

        try {
            var algoritmo = Algorithm.HMAC256(secreto);
            return JWT.require(algoritmo)

                    .withIssuer("API FORO HUB")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido o expirado");
        }

    }
}
