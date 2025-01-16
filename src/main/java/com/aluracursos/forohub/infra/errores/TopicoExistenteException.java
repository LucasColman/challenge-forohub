package com.aluracursos.forohub.infra.errores;

public class TopicoExistenteException extends RuntimeException {
    public TopicoExistenteException(String mensaje) {
        super(mensaje);
    }
}
