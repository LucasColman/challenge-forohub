package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    String findByEmail(String email);
}
