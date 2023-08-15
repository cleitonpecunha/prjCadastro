package io.github.cleitonpecunha.modules.Usuario.controller;

import io.github.cleitonpecunha.database.entitys.Usuario;
import io.github.cleitonpecunha.config.exception.RegraNegocioException;
import io.github.cleitonpecunha.modules.Usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid Usuario usuario) {
        try {
            Usuario salvarUsuario = usuarioService.salvarUsuario(usuario);
            return salvarUsuario.getId();
        } catch (RegraNegocioException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
