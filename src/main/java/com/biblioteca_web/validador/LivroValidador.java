package com.biblioteca_web.validador;

import com.biblioteca_web.exception.AutorNaoEncontradoException;
import com.biblioteca_web.exception.BuscaLivroException;
import com.biblioteca_web.exception.ExceptionPersonalizada;
import com.biblioteca_web.infraestructure.entities.Livro;
import com.biblioteca_web.infraestructure.repository.LivroRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroValidador {

    private final LivroRepository livroRepository;

    public LivroValidador(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // Busca livro pelo título ou lança exceção personalizada
    public List<Livro> buscaLivroOuLancaException(String titulo) {
        //return livroRepository.findByTitulo(titulo).orElseThrow(BuscaLivroException::new);
        if (titulo == null || titulo.isEmpty()) {
            throw new BuscaLivroException("Livro não encontrado!");
        }
        return livroRepository.findByTitulo(titulo);
    }

    // Valida se uma lista de livros está vazia
    public void validaSeListaEstaVazia(List<Livro> lista) throws ExceptionPersonalizada {
        if (lista == null || lista.isEmpty()) {
            throw new ExceptionPersonalizada("A lista de livros está vazia ou nula.");
        }
    }

    // Busca livros por autor ou lança exceção personalizada se não encontrar
    public List<Livro> validaSeLivroExiste(String autor) {
        List<Livro> livros = livroRepository.findByAutor(autor);
        if (livros == null || livros.isEmpty()) {
            throw new AutorNaoEncontradoException("Nenhum livro encontrado para o autor: " + autor);
        }
        return livros;
    }
}
