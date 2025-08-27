package com.biblioteca_web.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "livros")
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private String nome;
    private String categoria;
    private LocalDate dataPublicacao = LocalDate.now();


    public Livro(String titulo) {
        this.titulo = titulo;
        this.dataPublicacao = LocalDate.now();
    }

}

