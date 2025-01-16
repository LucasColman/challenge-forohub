package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.DatosCurso;
import com.aluracursos.forohub.domain.usuario.DatosUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        DatosUsuario autor,
        DatosCurso curso
) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                new DatosUsuario(topico.getAutor().getId(), topico.getAutor().getNombre(), topico.getAutor().getEmail()),
                        new DatosCurso(topico.getCurso().getId(), topico.getCurso().getNombre(), topico.getCurso().getCategoria()));
    }
}
