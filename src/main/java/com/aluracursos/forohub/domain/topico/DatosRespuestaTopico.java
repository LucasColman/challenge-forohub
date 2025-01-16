package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.DatosCurso;
import com.aluracursos.forohub.domain.usuario.DatosUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(

        Long id,
        String titulo,

        String mensaje,
        Estado status,
        LocalDateTime fechaCreacion,
        DatosCurso curso,
        DatosUsuario autor


) {
}
