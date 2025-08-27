package com.biblioteca_web.business;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.CadastrarLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.exception.ExceptionPersonalizada;
import com.biblioteca_web.infraestructure.entities.Livro;
import com.biblioteca_web.mapper.LivroMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.biblioteca_web.infraestructure.repository.LivroRepository;
import com.biblioteca_web.validador.LivroValidador;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroValidador livroValidador;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroValidador livroValidador, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroValidador = livroValidador;
        this.livroMapper = livroMapper;
    }

    public List<Livro> buscarLivroPorTitulo (String titulo) {
        List<Livro> livro = livroValidador.buscaLivroOuLancaException(titulo);
        return (livro);
    }

    public List<LivroDto> buscarTodos() throws ExceptionPersonalizada, ExceptionPersonalizada {
        List<Livro> user = livroRepository.findAll();
        livroValidador.validaSeListaEstaVazia(user);
        return livroMapper.listaDtoList(user);
    }

    public void deletarLivroPorTitulo(String titulo) {
        Livro livro = (Livro) livroValidador.buscaLivroOuLancaException(titulo);
        livroRepository.delete(livro);
    }

    public List<LivroDto> atualizarLivroPorId(String autor, AtualizaLivroDto dto) {
        Livro livro = (Livro) livroValidador.validaSeLivroExiste(autor);
        livroMapper.atualizaDto(dto, livro); livroRepository.save(livro);
        return (List<LivroDto>) livroMapper.toDto(livro);
    }

    public List<LivroDto> salvarLivros(List<CadastrarLivroDto> dtoList) {
        List<Livro> livros = dtoList.stream()
                .map(dto -> {
                    Livro livro = new Livro(dto.titulo());
                    livro.setNome(dto.nome());
                    livro.setTitulo(dto.titulo());
                    livro.setCategoria(dto.categoria());
                    livro.setIsbn(dto.isbn());
                    // Se dataPublicacao não vier, usar a data atual
                    livro.setDataPublicacao(dto.dataPublicacao() != null ? dto.dataPublicacao() : LocalDate.now());
                    return livro;
                })
                .collect(Collectors.toList());

        List<Livro> livrosSalvos = livroRepository.saveAll(livros);
        return livroMapper.listaDtoList(livrosSalvos);
    }


    public List<LivroDto> cadastrarLivros(List<CadastrarLivroDto> dtos) {
        // usa o mapper injetado, NÃO estático
        List<Livro> livros = livroMapper.toEntityList(dtos);
        List<Livro> livrosSalvos = livroRepository.saveAll(livros);
        return livroMapper.listaDtoList(livrosSalvos);
    }

    public List<LivroDto> listarLivros() {
        List<Livro> livros = livroRepository.findAll(); // pega entidades do banco

        // converte de Livro para LivroDto
        List<LivroDto> livrosDto = livros.stream()
                .map(l -> new LivroDto(
                        l.getId(),
                        l.getNome(),
                        l.getTitulo(),
                        l.getCategoria(),
                        l.getDataPublicacao(),
                        l.getIsbn()
                ))
                .collect(Collectors.toList());

        return livrosDto; // retorna DTOs para o controller
    }
}