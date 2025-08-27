package com.biblioteca_web.exception;

public class BuscaLivroException extends RuntimeException {
    private static final String LIVRO_NAO_ENCONTRADO = "Livro não encontrado!";

    public BuscaLivroException(String s) {
        super(LIVRO_NAO_ENCONTRADO);
    }
}
