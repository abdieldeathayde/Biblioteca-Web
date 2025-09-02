package com.biblioteca_web.infraestructure.repository;

import com.biblioteca_web.infraestructure.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTitulo(String titulo);       // busca pelo título exato
    List<Livro> findByAutor(String autor);         // busca pelo autor
    List<Livro> findByIsbn(String isbn);           // busca pelo ISBN
}
