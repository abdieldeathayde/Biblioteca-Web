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

    @DeleteMapping
    public ResponseEntity<String> deletarLivroPorTitulo(
            @RequestParam @NotBlank(message = "O titulo é obrigatório") String titulo) {
        livroService.deletarLivroPorTitulo(titulo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Livro deletado com sucesso!");
    }

    @PutMapping
    public ResponseEntity<LivroDto> atualizarLivroPorTitulo(
            @RequestParam @NotNull(message = "O titulo não pode ser nulo!") String titulo,
            @RequestBody @Valid AtualizaLivroDto livroDto){
        LivroDto livroAtualizado = livroService.atualizarLivroPorTitulo(titulo, livroDto);
        return ResponseEntity.ok(livroAtualizado);
    }

    @PatchMapping("/{titulo}")
    public ResponseEntity<LivroDto> atualizarParcialmente(@PathVariable String titulo,
                                                          @RequestBody Map<String, Object> updates) throws ExceptionPersonalizada {
        LivroDto recursoAtualizado = livroService.atualizarParcial(titulo, updates);
        return ResponseEntity.ok(recursoAtualizado);
    }
}
