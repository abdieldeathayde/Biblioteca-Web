package com.biblioteca_web.mapper;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.CadastrarLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.dto.response.RespostaCadastroLivroDTO;
import com.biblioteca_web.infraestructure.entities.Livro;
import jakarta.validation.Valid;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    LivroDto toDto(Livro livro);

    // Converter DTO individual para entidade
    Livro toEntity(CadastrarLivroDto dto);

    List<LivroDto> listaDtoList(List<Livro> livros);

    List<RespostaCadastroLivroDTO> converteLista(List<Livro> livros);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizaDto(AtualizaLivroDto dto, @MappingTarget Livro livro);

    // Agora MapStruct consegue mapear cada elemento da lista automaticamente
    List<Livro> toEntityList(@Valid List<CadastrarLivroDto> dtoList);

    List<RespostaCadastroLivroDTO> toRespostaCadastroLivroDTOList(List<Livro> livros);
}