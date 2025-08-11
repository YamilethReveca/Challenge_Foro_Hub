package com.alura.Foro.Hub.challenge.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String titulo;

    @Column(unique = true)
    @NotBlank
    private String mensaje;

    @NotNull
    private LocalDate fechaDeCreacion;

    @NotBlank
    private String status;

    @NotBlank
    private String autor;

    @NotBlank
    private String curso;

    @NotBlank
    private String respuesta;

    public Topico(DatosRegistroTopico datos) {
        this.id=null;
        this.titulo=datos.titulo();
        this.mensaje= datos.mensaje();
        this.fechaDeCreacion=datos.fechaDeCreacion();
        this.status= datos.status();
        this.autor= datos.autor();
        this.curso= datos.curso();
        this.respuesta= datos.respuesta();
    }

    public void actualizarInformaciones(@Valid DatosActualizarTopico datos) {

        if(datos.mensaje() != null){
            this.mensaje=datos.mensaje();
        }
        if(datos.status() != null){
            this.status=datos.status();
        }
        if(datos.autor() != null){
            this.autor=datos.autor();
        }
        if(datos.curso() != null){
            this.curso=datos.curso();
        }
        if(datos.respuesta() != null){
            this.respuesta=datos.respuesta();
        }
    }
}
