package dev.samuel.auth_service_grocery_story.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "O e-mail é obrigatório")
        @Email
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8)
        String password

) {}
