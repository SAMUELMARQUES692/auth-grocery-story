package dev.samuel.auth_service_grocery_story.response;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        List<String> scopes
) {}
