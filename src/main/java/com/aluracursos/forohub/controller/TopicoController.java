package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.domain.curso.DatosCurso;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.DatosUsuario;
import com.aluracursos.forohub.services.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }


    //Registrar topico
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriBuilder){

        Topico topico = topicoService.crearTopico(datosRegistroTopico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getStatus(),topico.getFechaCreacion(),
                new DatosCurso(topico.getCurso().getId(), topico.getCurso().getNombre(), topico.getCurso().getCategoria()),
                new DatosUsuario(topico.getAutor().getId(),topico.getAutor().getNombre(), topico.getAutor().getEmail()));


            URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(datosRespuestaTopico);

    }

    //Listar topicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@RequestParam(required = false) String curso,
            Pageable paginacion) {

        Page<Topico> topicos;

        if (curso != null) {
            topicos = topicoService.listarTopicosPorCurso(curso, paginacion);
        } else {
            topicos = topicoService.listarTopicos(paginacion);
        }

        var page = topicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> buscarTopicoPorId(@PathVariable Long id){
        Topico topico = topicoService.buscarTopicoPorId(id);
        return getDatosRespuestaTopicoResponseEntity(topico);
    }

    //Actualizar Topico
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoService.buscarTopicoPorId(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return getDatosRespuestaTopicoResponseEntity(topico);

    }

    private ResponseEntity<DatosRespuestaTopico> getDatosRespuestaTopicoResponseEntity(Topico topico) {
        var datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getStatus(),topico.getFechaCreacion(),
                new DatosCurso(topico.getCurso().getId(), topico.getCurso().getNombre(), topico.getCurso().getCategoria()),
                new DatosUsuario(topico.getAutor().getId(),topico.getAutor().getNombre(), topico.getAutor().getEmail()));

        return ResponseEntity.ok(datosRespuestaTopico);
    }

    //Eliminar Topico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
