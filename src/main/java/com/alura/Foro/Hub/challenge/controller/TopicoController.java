package com.alura.Foro.Hub.challenge.controller;

import com.alura.Foro.Hub.challenge.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;
    @PostMapping
    @Transactional
     public void crear(@RequestBody @Valid DatosRegistroTopico datos){
        repository.save(new Topico(datos));

    }

    @GetMapping
    public Page<DatosListaTopicos> listarTopicos(@PageableDefault(size = 10, sort = {"fechaDeCreacion"}) Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListaTopicos::new);
    }

    @GetMapping("/{id}")
    public DatosListaTopicos obtenerTopico(@PathVariable Long id){

        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            return new DatosListaTopicos(optionalTopico.get());
        } else {
            return null;
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos, @PathVariable Long id){
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarInformaciones(datos);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = repository.findById(id);
        if (optionalTopico.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
