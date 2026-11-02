package dev.samuel.auth_service_grocery_story.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "O Campo nome é obrigatório")
        String name,

        @NotBlank(message = "O Campo e-mail é obrigatório")
        String email,

        @NotBlank(message = "O Campo senha é obrigatório")
        String password
) {}
