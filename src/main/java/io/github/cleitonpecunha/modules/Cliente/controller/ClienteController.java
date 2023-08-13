package io.github.cleitonpecunha.modules.Cliente.controller;

import io.github.cleitonpecunha.database.entity.Cliente;
import io.github.cleitonpecunha.exception.RegraNegocioException;
import io.github.cleitonpecunha.modules.Cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid Cliente cliente) {
        try {
            Cliente salvarCliente = clienteService.salvarCliente(cliente);
            return salvarCliente.getId();
        } catch (RegraNegocioException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        try {
            Cliente excluirCliente = clienteService.excluirCliente(id);
        } catch (RegraNegocioException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Cliente cliente) {
        try {
            Cliente modificarCliente = clienteService.modificarCliente(id, cliente);
        } catch (RegraNegocioException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteById(@PathVariable Integer id) {
        try {
            return clienteService.buscarClienteId(id);
        } catch (RegraNegocioException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public Page<Cliente> list(@RequestParam(required = false) Map<String, String> filters,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
            return clienteService.listarCliente(filters, PageRequest.of(page, size));
    }

}