package com.aluracursos.forohub.services;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.infra.errores.TopicoExistenteException;
import com.aluracursos.forohub.repository.CursoRepository;
import com.aluracursos.forohub.repository.TopicoRepository;
import com.aluracursos.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }


    //POST
    @Transactional
    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.autor())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + datosRegistroTopico.autor()));

        Curso curso = cursoRepository.findById(datosRegistroTopico.curso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + datosRegistroTopico.curso()));

        Topico topicoExistente = topicoRepository.findByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());


        if (topicoExistente != null) {
            throw new TopicoExistenteException("Ya existe un topico con el titulo: " + datosRegistroTopico.titulo() +
                    " y mensaje: " + datosRegistroTopico.mensaje());
        }

        Topico topico = new Topico();
        topico.setTitulo(datosRegistroTopico.titulo());
        topico.setMensaje(datosRegistroTopico.mensaje());
        topico.setStatus(Estado.ABIERTO);
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }

    //GET
    public Page<Topico> listarTopicos(Pageable pageable) {
        Sort orden = Sort.by(Sort.Order.desc("fechaCreacion"), Sort.Order.asc("curso"));
        return topicoRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                orden
        ));
    }

    public Page<Topico> listarTopicosPorCurso(String nombreCurso, Pageable pageable) {
        return topicoRepository.findByCursoNombre(nombreCurso, pageable);
    }

    public Topico buscarTopicoPorId(Long id) {
        return topicoRepository.getReferenceById(id);
    }

    //DELETE
    public void eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id);

        if(topico.isPresent()){
            topicoRepository.delete(topico.get());
        }else{
            throw new IllegalArgumentException("Topico no encontrado con ID: " + id);
        }
    }
}
