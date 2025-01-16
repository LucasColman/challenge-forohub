package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Topico findByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCursoNombre(String nombreCurso, Pageable pageable);

}

