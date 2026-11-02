package dev.samuel.auth_service_grocery_story.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "O Campo nome é obrigatório")
        String name,


        @NotBlank(message = "O Campo e-mail é obrigatório")
        @Email
        String email,

        @NotBlank(message = "O Campo senha é obrigatório")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        String password
) {}
