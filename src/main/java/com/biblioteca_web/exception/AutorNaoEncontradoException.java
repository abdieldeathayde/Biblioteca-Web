package com.biblioteca_web.exception;

public class AutorNaoEncontradoException extends RuntimeException {
    private static final String AUTOR_NAO_ENCONTRADO = "Autor não foi encontrado!";

    public AutorNaoEncontradoException(String s) {
        super(AUTOR_NAO_ENCONTRADO);
    }
}
