package dev.samuel.auth_service_grocery_story.service;

import dev.samuel.auth_service_grocery_story.entity.Scope;
import dev.samuel.auth_service_grocery_story.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public String gerarToken(User user) {
        Instant now = Instant.now();

        String scopesStr = user.getScopes().stream()
                .map(Scope::getName)
                .collect(Collectors.joining(" ")); // padrão OAuth2: scopes separados por espaço

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("auth-service-grocery")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(user.getEmail())
                .claim("scope", scopesStr)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(SignatureAlgorithm.RS256).build(),
                claimsSet
        )).getTokenValue();
    }



}
