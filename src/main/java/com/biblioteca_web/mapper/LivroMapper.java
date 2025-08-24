package com.biblioteca_web.mapper;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.infraestructure.entities.Livro;

import java.util.List;

public class LivroMapper {
    public Livro toEntity(List<Livro> dto) {

    }

    public LivroDto toDto(Livro usuario) {
    }

    public List<LivroDto> converteLista(List<Livro> user) {
    }

    public void atualizaDto(AtualizaLivroDto dto, Livro livro) {
    }


    public List<Livro> toEntityList(List<LivroDto> dtoList) {
        return List.of();
    }
}
