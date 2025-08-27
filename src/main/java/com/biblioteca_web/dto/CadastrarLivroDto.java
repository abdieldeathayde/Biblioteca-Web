package com.biblioteca_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CadastrarLivroDto(
        @NotBlank(message = "Campo Nome é obrigatório")
        String nome,
        @NotBlank(message = "Campo Categoria é obrigatório")
        String categoria,

        @NotBlank(message = "Campo Título é obrigatório")
        String titulo,
        @NotBlank(message = "Campo isbn é obrigatório")
        String isbn,
        LocalDate dataPublicacao
) {

}
