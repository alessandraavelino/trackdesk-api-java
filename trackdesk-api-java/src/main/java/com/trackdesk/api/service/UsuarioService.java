package com.trackdesk.api.service;
import com.trackdesk.api.model.UsuarioModel;
import com.trackdesk.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioModel> listar() {
        return repository.findAll();
    }

    public UsuarioModel salvar(UsuarioModel usuario) {
        return repository.save(usuario);
    }

    public UsuarioModel buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
