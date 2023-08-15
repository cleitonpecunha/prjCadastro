package io.github.cleitonpecunha.database.repositories;

import io.github.cleitonpecunha.database.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Clientes extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    boolean existsByCpf(String cpf);

}
