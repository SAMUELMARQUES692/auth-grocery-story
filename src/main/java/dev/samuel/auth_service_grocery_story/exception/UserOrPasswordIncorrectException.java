package dev.samuel.auth_service_grocery_story.exception;

public class UserOrPasswordIncorrectException extends RegraNegocioException {
    public UserOrPasswordIncorrectException(String email, String password) {
        super("SENHA_OU_EMAIL_INCORRETOS", "Email: " + email + "ou Senha: " + password +  "estão inválidos");
    }
}
