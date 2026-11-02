package dev.samuel.auth_service_grocery_story.exception;

public abstract class SemAutorizacaoException extends RuntimeException {
    private final String codigo;

    protected SemAutorizacaoException(String codigo, String mensagem) {
        super(mensagem);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
