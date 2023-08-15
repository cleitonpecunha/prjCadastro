package io.github.cleitonpecunha.modules.Cliente.service;

import io.github.cleitonpecunha.database.entitys.Cliente;
import io.github.cleitonpecunha.database.repositories.Clientes;
import io.github.cleitonpecunha.config.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static io.github.cleitonpecunha.modules.Cliente.specifications.ClienteSpecification.filterWithOptions;

@Service
@RequiredArgsConstructor

public class ClienteServiceImpl implements ClienteService {

    private final Clientes clientesRepository;

    @Override
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {

        if( clientesRepository.existsByCpf(cliente.getCpf()) ) {
            throw new RegraNegocioException("O CPF informado já se encontra cadastrado para outro cliente");
        }
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(cliente.getNome());
        novoCliente.setCpf(cliente.getCpf());
        clientesRepository.save(novoCliente);
        return novoCliente;
    }

    @Override
    @Transactional
    public Cliente excluirCliente(Integer id) {
        Optional<Cliente> clienteO = clientesRepository.findById(id);
        if (!clienteO.isPresent())  {
            throw new RegraNegocioException("Cliente não encontrado para excluir!");
        }
        clientesRepository.delete(clienteO.get());
        return clienteO.get();
    }

    @Override
    @Transactional
    public Cliente modificarCliente(Integer id, Cliente cliente) {
        Optional<Cliente> clienteO = clientesRepository.findById(id);
        if (!clienteO.isPresent())  {
            throw new RegraNegocioException("Cliente não encontrado para atualizar informações!");
        }
        cliente.setId(id);
        clientesRepository.save(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Cliente buscarClienteId(Integer id) {
        Optional<Cliente> clienteO = clientesRepository.findById(id);
        if (!clienteO.isPresent())  {
            throw new RegraNegocioException("Cliente não encontrado na pesquisa!");
        }
        return clienteO.get();
    }

    @Override
    public Page<Cliente> listarCliente(Map<String, String> filters, Pageable pageable) {
        return clientesRepository.findAll(filterWithOptions(filters), pageable);
    }

}
