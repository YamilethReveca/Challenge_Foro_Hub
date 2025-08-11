package com.alura.Foro.Hub.challenge.topico;

import java.time.LocalDate;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        String status,
        String autor,
        String curso,
        String respuesta ) {
}
