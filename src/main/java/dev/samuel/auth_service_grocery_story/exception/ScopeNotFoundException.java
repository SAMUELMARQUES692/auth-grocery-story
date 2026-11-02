package dev.samuel.auth_service_grocery_story.exception;

public class ScopeNotFoundException extends RecursoNaoEncontradoException {
    public ScopeNotFoundException(String name) {
        super("SCOPE_NAO_ENCONTRADO", "Scope de nome: " + name + " não encontrado");
    }

}
