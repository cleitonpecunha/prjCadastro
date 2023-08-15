package io.github.cleitonpecunha.database.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_usuario")
    @NotEmpty(message = "{usuario.nome.obrigatorio}")
    private String nome;

    @Column
    @NotEmpty(message = "{usuario.login.obrigatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{usuario.senha.obrigatorio}")
    private String senha;

    @Column(name = "administrador")
    private boolean admin;
}