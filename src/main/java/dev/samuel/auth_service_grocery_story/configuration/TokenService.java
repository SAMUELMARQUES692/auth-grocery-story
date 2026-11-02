package dev.samuel.auth_service_grocery_story.configuration;

import dev.samuel.auth_service_grocery_story.entity.Scope;
import dev.samuel.auth_service_grocery_story.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
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

    @Bean
    public JwtDecoder jwtDecoder(RSAPublicKey rsaPublicKey) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
        decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer("auth-service-grocery"));
        return decoder;
    }

}
