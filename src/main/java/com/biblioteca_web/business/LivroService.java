package com.biblioteca_web.business;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.CadastrarLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.exception.ExceptionPersonalizada;
import com.biblioteca_web.infraestructure.entities.Livro;
import com.biblioteca_web.infraestructure.repository.LivroRepository;
import com.biblioteca_web.mapper.LivroMapper;
import com.biblioteca_web.validador.LivroValidador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroValidador livroValidador;
    private final LivroMapper livroMapper;

    /**
     * Cadastrar um único livro
     */
    public LivroDto salvarLivro(@Valid CadastrarLivroDto dto) {
        Livro livro = livroMapper.toEntity(dto);
        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }


    /**
     * Cadastrar uma lista de livros
     */
    public List<LivroDto> salvarLivros(@Valid List<CadastrarLivroDto> dtoList) {
        List<Livro> livros = livroMapper.toEntityList(dtoList);
        List<Livro> salvos = livroRepository.saveAll(livros);
        return livroMapper.toDtoList(salvos);
    }


    /**
     * Buscar livro por título
     */
    public List<Livro> buscarLivroPorTitulo(String titulo) throws ExceptionPersonalizada {
        return livroRepository.findByTitulo(titulo);
    }

    /**
     * Buscar todos os livros
     */
    public List<LivroDto> buscarTodos() throws ExceptionPersonalizada {
        List<Livro> livros = livroRepository.findAll();
        livroValidador.validaSeListaEstaVazia(livros);
        return livroMapper.toDtoList(livros);
    }

    /**
     * Deletar livro pelo título
     */
    public void deletarLivroPorTitulo(String titulo) {
        List<Livro> livros = livroValidador.buscaLivroOuLancaException(titulo);
        livroRepository.deleteAll(livros);
    }



    /**
     * Atualizar livro por título (ou autor, se quiser)
     */
    public LivroDto atualizarLivroPorTitulo(String titulo, AtualizaLivroDto dto) {
        Livro livro = (Livro) livroValidador.validaSeLivroExiste(titulo);
        livroMapper.atualizaDto(dto, livro);
        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

    /**
     * Listar todos os livros (versão manual sem mapper)
     */
    public List<LivroDto> listarLivros() {
        return livroRepository.findAll().stream()
                .map(l -> new LivroDto(
                        l.getId(),
                        l.getNome(),
                        l.getTitulo(),
                        l.getCategoria(),
                        l.getDataPublicacao(),
                        l.getIsbn()
                ))
                .collect(Collectors.toList());
    }



    /**
     * Atualização parcial (PATCH)
     */



    public LivroDto atualizarParcial(String titulo, Map<String, Object> updates) throws ExceptionPersonalizada {
        Livro livro = (Livro) buscarLivroPorTitulo(titulo);

        updates.forEach((campo, valor) -> {
            switch (campo) {
                case "nome" -> livro.setNome((String) valor);
                case "titulo" -> livro.setTitulo((String) valor);
                case "categoria" -> livro.setCategoria((String) valor);
                case "isbn" -> livro.setIsbn((String) valor);
                case "dataPublicacao" -> livro.setDataPublicacao(LocalDate.parse((String) valor));
            }
        });

        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

}
