package dev.samuel.auth_service_grocery_story.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        String tokenType,
        Long expiresIn
) {}
