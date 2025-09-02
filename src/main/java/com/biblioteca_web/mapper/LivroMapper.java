package com.biblioteca_web.mapper;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.CadastrarLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.infraestructure.entities.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    // --- Entity -> DTO ---
    LivroDto toDto(Livro livro); // converte 1 livro

    List<LivroDto> toDtoList(List<Livro> livros); // converte lista de livros

    // --- DTO -> Entity ---
    Livro toEntity(CadastrarLivroDto dto); // converte 1 DTO

    List<Livro> toEntityList(List<CadastrarLivroDto> dtoList); // converte lista de DTOs

    // --- Atualização parcial ---
    void atualizaDto(AtualizaLivroDto dto, @MappingTarget Livro livro);
}
