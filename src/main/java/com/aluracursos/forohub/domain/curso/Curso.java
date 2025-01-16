package com.aluracursos.forohub.domain.curso;


import com.aluracursos.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

//    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
//    private List<Topico> topicos;

    public Curso() {
    }

    public Curso(Long id, String nombre, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
