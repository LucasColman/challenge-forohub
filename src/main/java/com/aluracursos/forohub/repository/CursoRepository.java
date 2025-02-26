package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    String findByNombre(String nombre);
}
