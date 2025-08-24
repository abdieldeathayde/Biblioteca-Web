package com.biblioteca_web.business;

import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.infraestructure.entities.Livro;
import com.biblioteca_web.infraestructure.repository.LivroRepository;
import com.biblioteca_web.mapper.LivroMapper;
import com.biblioteca_web.validador.LivroValidador;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository usuarioRepository;
    private final LivroValidador usuarioValidador;
    private final LivroMapper LivroMapper;

    public LivroDto salvarLivro(List<Livro> listaDto) {
        Livro usuario = LivroMapper.toEntity(listaDto);
        usuarioRepository.save(usuario);
        return LivroMapper.toDto(usuario);
    }

    public LivroDto buscarLivroPorEmail(String email) {
        Livro usuario = usuarioValidador.buscaLivroOuLancaException(email);
        return LivroMapper.toDto(usuario);
    }

    public List<LivroDto> buscarTodos() {
        List<Livro> user = usuarioRepository.findAll();
        usuarioValidador.validaSeListaEstaVazia(user);
        return LivroMapper.converteLista(user);
    }



    public void deletarLivroPorEmail(String email) {
        Livro usuario = usuarioValidador.buscaLivroOuLancaException(email);
        usuarioRepository.delete(usuario);
    }


    public LivroDto atualizarLivroPorId(Integer id, AtualizaLivroDto dto) {
        Livro livro = usuarioValidador.validaSeLivroExiste(id);
        LivroMapper.atualizaDto(dto, livro);
        Livro Livro = new Livro();
        usuarioRepository.save(Livro);
        return LivroMapper.toDto(Livro);
    }

    public List<LivroDto> salvarLivros(List<LivroDto> dtoList) {
        // Converte a lista de DTOs de criação para entidades Livro
        List<Livro> Livros = LivroMapper.toEntityList(dtoList);

        // Salva todos de uma vez no banco
        List<Livro> LivrosSalvos = LivroRepository.saveAll(Livros);

        // Converte a lista salva para DTOs de resposta
        return LivroMapper.converteLista(LivrosSalvos);
    }


}