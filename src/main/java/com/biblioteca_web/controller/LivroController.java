package com.biblioteca_web.controller;

import com.biblioteca_web.business.LivroService;
import com.biblioteca_web.dto.AtualizaLivroDto;
import com.biblioteca_web.dto.CadastrarLivroDto;
import com.biblioteca_web.dto.LivroDto;
import com.biblioteca_web.exception.ExceptionPersonalizada;
import com.biblioteca_web.infraestructure.entities.Livro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<List<LivroDto>> cadastrarLivro(@RequestBody @Valid List<CadastrarLivroDto> livroDto) {
        List<LivroDto> livroCadastrado = livroService.salvarLivros(livroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> listarTodos() {
        List<LivroDto> livroDto = livroService.listarLivros();
        return ResponseEntity.ok(livroDto);
    }

    @SneakyThrows
    @GetMapping("/filtrar")
    public ResponseEntity<List<LivroDto>> listarFiltrados(@RequestBody List<Livro> livro) {
        List<LivroDto> livroDto = livroService.listarLivros();
        return ResponseEntity.ok(livroDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivroPorId(@PathVariable Long id) {
        livroService.deletarLivroPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Livro deletado com sucesso!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizarLivroPorId(
            @PathVariable Long id,
            @RequestBody @Valid AtualizaLivroDto livroDto) {
        LivroDto livroAtualizado = livroService.atualizarLivroPorId(id, livroDto);
        return ResponseEntity.ok(livroAtualizado);
    }



}
