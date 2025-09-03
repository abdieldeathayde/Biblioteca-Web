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

    public LivroDto salvarLivro(@Valid CadastrarLivroDto dto) {
        Livro livro = livroMapper.toEntity(dto);
        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

    public List<LivroDto> salvarLivros(@Valid List<CadastrarLivroDto> dtoList) {
        List<Livro> livros = livroMapper.toEntityList(dtoList);
        List<Livro> salvos = livroRepository.saveAll(livros);
        return livroMapper.toDtoList(salvos);
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) throws ExceptionPersonalizada {
        return livroRepository.findByTitulo(titulo);
    }

    public List<LivroDto> buscarTodos() throws ExceptionPersonalizada {
        List<Livro> livros = livroRepository.findAll();
        livroValidador.validaSeListaEstaVazia(livros);
        return livroMapper.toDtoList(livros);
    }

    public void deletarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro com ID " + id + " não encontrado"));
        livroRepository.delete(livro);
    }




    public LivroDto atualizarLivroPorId(Long id, AtualizaLivroDto dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro com ID " + id + " não encontrado"));
        livroMapper.atualizaDto(dto, livro);
        livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

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


}
