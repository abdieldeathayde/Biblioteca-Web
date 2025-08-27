package com.biblioteca_web.dto.response;

import java.time.LocalDate;

public record RespostaCadastroLivroDTO(
        String titulo,
        String nome,
        String isbn,
        LocalDate dataPublicacao,
        String categoria
) {
}
