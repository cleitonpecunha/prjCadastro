package io.github.cleitonpecunha.database.repositories;

import io.github.cleitonpecunha.database.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuarios extends JpaRepository<Usuario, Integer> {
}
