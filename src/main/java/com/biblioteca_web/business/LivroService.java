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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LivroService {

    @Autowired
    private final LivroRepository livroRepository;
    private final LivroValidador livroValidador;
    private final LivroMapper livroMapper;

    /**
     * Cadastrar um único livro
     */
    public LivroDto salvarLivro(@Valid CadastrarLivroDto dto) {
        Livro livro = livroMapper.toEntity(dto);
        // Se não vier data de publicação, usar a data atual
        if (livro.getDataPublicacao() == null) {
            livro.setDataPublicacao(LocalDate.now());
        }
        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

    /**
     * Cadastrar uma lista de livros
     */
    public List<LivroDto> salvarLivros(@Valid List<CadastrarLivroDto> dtoList) {
        List<Livro> livros = livroMapper.toEntityList(dtoList);
        livros.forEach(l -> {
            if (l.getDataPublicacao() == null) {
                l.setDataPublicacao(LocalDate.now());
            }
        });
        List<Livro> livrosSalvos = livroRepository.saveAll(livros);
        return livroMapper.listaDtoList(livrosSalvos);
    }

    /**
     * Buscar livros por título
     */
    public List<Livro> buscarLivroPorTitulo(String titulo) {
        return livroValidador.buscaLivroOuLancaException(titulo);
    }

    /**
     * Buscar todos os livros
     */
    public List<LivroDto> buscarTodos() throws ExceptionPersonalizada {
        List<Livro> livros = livroRepository.findAll();
        livroValidador.validaSeListaEstaVazia(livros);
        return livroMapper.listaDtoList(livros);
    }

    /**
     * Deletar livro pelo título
     */
    public void deletarLivroPorTitulo(String titulo) {
        Livro livro = livroValidador.buscaLivroOuLancaException(titulo).get(0);
        livroRepository.delete(livro);
    }

    /**
     * Atualizar livro por autor
     */
    public LivroDto atualizarLivroPorId(String autor, AtualizaLivroDto dto) {
        Livro livro = (Livro) livroValidador.validaSeLivroExiste(autor);
        livroMapper.atualizaDto(dto, livro);
        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

    /**
     * Listar todos os livros (versão manual sem mapper)
     */
    public List<LivroDto> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
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
}
