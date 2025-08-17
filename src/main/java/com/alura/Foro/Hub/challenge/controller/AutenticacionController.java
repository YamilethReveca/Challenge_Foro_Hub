package com.alura.Foro.Hub.challenge.controller;

import com.alura.Foro.Hub.challenge.infra.security.DatosTokenJWT;
import com.alura.Foro.Hub.challenge.infra.security.TokenService;
import com.alura.Foro.Hub.challenge.usuario.DatosAutenticacion;
import com.alura.Foro.Hub.challenge.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    public AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var autenticationToken= new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autentication = manager.authenticate(autenticationToken);

        var tokenJWT= tokenService.generarToken((Usuario) autentication.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }


}
