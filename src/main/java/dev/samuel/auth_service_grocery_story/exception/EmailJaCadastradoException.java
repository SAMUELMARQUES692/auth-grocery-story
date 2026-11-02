package dev.samuel.auth_service_grocery_story.exception;

public class EmailJaCadastradoException extends RegraNegocioException {
    public EmailJaCadastradoException(String email) {
        super("EMAIL_JA_EXISTENTE", "Email: " + email + " já está em uso, escolha outro e-mail");
    }
}
