package com.alura.Foro.Hub.challenge.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosActualizarTopico(
        String mensaje,
        String status,
        String autor,
        String curso,
        String respuesta
) {
}
