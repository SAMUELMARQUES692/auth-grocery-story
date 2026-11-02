package dev.samuel.auth_service_grocery_story.service;

import dev.samuel.auth_service_grocery_story.entity.Scope;
import dev.samuel.auth_service_grocery_story.entity.User;
import dev.samuel.auth_service_grocery_story.exception.EmailJaCadastradoException;
import dev.samuel.auth_service_grocery_story.mapper.UserMapper;
import dev.samuel.auth_service_grocery_story.repository.UserRepository;
import dev.samuel.auth_service_grocery_story.request.UserRequest;
import dev.samuel.auth_service_grocery_story.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ScopeService scopeService;

    @Transactional
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException(request.email());
        }

        String email = request.email().trim().toLowerCase();

        Scope scopeUser = scopeService.findByName("USER");

        User newUser = userMapper.toEntity(request);
        newUser.setScopes(List.of(scopeUser));
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(request.password()));
        User save = userRepository.save(newUser);
        return userMapper.toUserResponse(save);
    }
}
