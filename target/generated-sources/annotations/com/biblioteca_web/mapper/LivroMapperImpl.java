package com.biblioteca_web.mapper;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.CadastrarLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.infraestructure.entities.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-02T18:44:48-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl implements LivroMapper {

    @Override
    public LivroDto toDto(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        LivroDto livroDto = new LivroDto();

        livroDto.setId( livro.getId() );
        livroDto.setNome( livro.getNome() );
        livroDto.setTitulo( livro.getTitulo() );
        livroDto.setCategoria( livro.getCategoria() );
        livroDto.setDataPublicacao( livro.getDataPublicacao() );
        livroDto.setIsbn( livro.getIsbn() );

        return livroDto;
    }

    @Override
    public List<LivroDto> toDtoList(List<Livro> livros) {
        if ( livros == null ) {
            return null;
        }

        List<LivroDto> list = new ArrayList<LivroDto>( livros.size() );
        for ( Livro livro : livros ) {
            list.add( toDto( livro ) );
        }

        return list;
    }

    @Override
    public Livro toEntity(CadastrarLivroDto dto) {
        if ( dto == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setTitulo( dto.titulo() );
        livro.setIsbn( dto.isbn() );
        livro.setNome( dto.nome() );
        livro.setCategoria( dto.categoria() );
        livro.setDataPublicacao( dto.dataPublicacao() );

        return livro;
    }

    @Override
    public List<Livro> toEntityList(List<CadastrarLivroDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Livro> list = new ArrayList<Livro>( dtoList.size() );
        for ( CadastrarLivroDto cadastrarLivroDto : dtoList ) {
            list.add( toEntity( cadastrarLivroDto ) );
        }

        return list;
    }

    @Override
    public void atualizaDto(AtualizaLivroDto dto, Livro livro) {
        if ( dto == null ) {
            return;
        }

        livro.setTitulo( dto.titulo() );
        livro.setIsbn( dto.isbn() );
        livro.setNome( dto.nome() );
        livro.setCategoria( dto.categoria() );
        livro.setDataPublicacao( dto.dataPublicacao() );
    }
}
