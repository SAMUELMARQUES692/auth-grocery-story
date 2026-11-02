package dev.samuel.auth_service_grocery_story.service;

import dev.samuel.auth_service_grocery_story.entity.User;
import dev.samuel.auth_service_grocery_story.exception.UserOrPasswordIncorrectException;
import dev.samuel.auth_service_grocery_story.repository.UserRepository;
import dev.samuel.auth_service_grocery_story.request.LoginRequest;
import dev.samuel.auth_service_grocery_story.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private static final long EXPIRES_IN_SECONDS = 3600L;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email().trim().toLowerCase())
                .filter(u -> passwordEncoder.matches(request.password(), u.getPassword()))
                .orElseThrow(() -> new UserOrPasswordIncorrectException());

        String token = tokenService.gerarToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(EXPIRES_IN_SECONDS)
                .build();
    }

    private boolean isPasswordCorrect(String password, String savePassword) {
        return passwordEncoder.matches(password, savePassword);
    }

}
