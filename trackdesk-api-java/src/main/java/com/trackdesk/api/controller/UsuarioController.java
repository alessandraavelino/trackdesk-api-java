package com.trackdesk.api.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.trackdesk.api.model.UsuarioModel;
import com.trackdesk.api.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioModel> listar() {
        return service.listar();
    }

    @PostMapping
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return service.salvar(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            UsuarioModel usuario = service.buscar(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("Usuário não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("erro", "Usuário não encontrado"));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Erro interno ao buscar usuário"));
        }
    }





    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public UsuarioModel update(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        UsuarioModel checkExist = service.buscar(id);
        checkExist.setNome(usuario.getNome());
        checkExist.setEmail(usuario.getEmail());
        return service.salvar(checkExist);
    }
}
