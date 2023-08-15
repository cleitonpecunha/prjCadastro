package io.github.cleitonpecunha.database.repositories;

import io.github.cleitonpecunha.database.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Usuarios extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

    boolean existsByLogin(String login);

}
