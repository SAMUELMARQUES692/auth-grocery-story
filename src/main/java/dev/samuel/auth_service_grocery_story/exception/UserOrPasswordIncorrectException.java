package dev.samuel.auth_service_grocery_story.exception;

public class UserOrPasswordIncorrectException extends SemAutorizacaoException {
    public UserOrPasswordIncorrectException() {
        super("CREDENCIAIS_INVALIDAS", "E-mail ou senha inválidos");
    }
}
