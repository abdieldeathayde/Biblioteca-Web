package com.biblioteca_web.exception;

public class ExceptionPersonalizada extends Throwable {
    private static final String VALIDA_SE_LISTA_ESTA_VAZIA = "Lista está vazia!";

    public ExceptionPersonalizada(String s) {
        super(VALIDA_SE_LISTA_ESTA_VAZIA);
    }

}
