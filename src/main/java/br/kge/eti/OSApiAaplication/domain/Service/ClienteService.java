/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.kge.eti.OSApiAaplication.domain.Service;

import br.kge.eti.OSApiAaplication.domain.exception.DomainException;
import br.kge.eti.OSApiAaplication.domain.model.Cliente;
import br.kge.eti.OSApiAaplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
    Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());


if (clienteExistente != null && !clienteExistente.equals(cliente)) {
    throw new DomainException("Ja existe um cliente cadastrado com esse email!");
}
    return clienteRepository.save(cliente);
    }
public void excluir(Long clienteId) {
clienteRepository.deleteById(clienteId);
}}
