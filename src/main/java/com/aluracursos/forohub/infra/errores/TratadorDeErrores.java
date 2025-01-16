package com.aluracursos.forohub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<DatosErrorValidacion> tratarError500(Exception e) {
        DatosErrorValidacion errorResponse = new DatosErrorValidacion(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private record DatosErrorValidacion(int status, String campo, String mensaje, LocalDateTime fecha){
        public DatosErrorValidacion(FieldError error){
            this(error.hashCode(), error.getField(), error.getDefaultMessage(), LocalDateTime.now());
        }
    }

}
