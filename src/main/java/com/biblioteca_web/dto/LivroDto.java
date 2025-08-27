package com.biblioteca_web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {
    private Long id;
    private String nome;
    private String titulo;
    private String categoria;
    private LocalDate dataPublicacao = LocalDate.now();
    private String isbn;
}
