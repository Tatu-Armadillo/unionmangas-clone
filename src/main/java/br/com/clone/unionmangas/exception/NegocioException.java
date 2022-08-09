package br.com.clone.unionmangas.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(final String mensagem, final String... param) {
        super(mensagem);
    }
}
