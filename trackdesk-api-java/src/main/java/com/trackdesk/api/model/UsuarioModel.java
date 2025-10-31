package com.trackdesk.api.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
}
