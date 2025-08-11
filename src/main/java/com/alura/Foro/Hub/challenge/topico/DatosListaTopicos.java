package com.alura.Foro.Hub.challenge.topico;

import java.time.LocalDate;

public record DatosListaTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        String status,
        String autor,
        String curso,
        String respuesta) {


    public DatosListaTopicos(Topico topico) {
        this( topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getRespuesta()
        );

    }
}
