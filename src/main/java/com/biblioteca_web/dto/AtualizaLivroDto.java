package com.biblioteca_web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizaLivroDto(
        @NotBlank(message = "Título é obrigatório")
        String titulo,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "ISBN é obrigatório")
        String isbn,
        @NotNull(message = "Data da publicação é obrigatória")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dataPublicacao,
        @NotBlank(message = "Categoria é obrigatório")
        String categoria
) {
}
