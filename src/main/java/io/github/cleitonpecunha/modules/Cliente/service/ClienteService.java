package io.github.cleitonpecunha.modules.Cliente.service;

import io.github.cleitonpecunha.database.entitys.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ClienteService {

    Cliente salvarCliente(Cliente cliente);

    Cliente excluirCliente(Integer id);

    Cliente modificarCliente(Integer id, Cliente cliente);

    Cliente buscarClienteId(Integer id);

    Page<Cliente> listarCliente(Map<String, String> filters, Pageable pageable);
}
