package br.com.fiap.techchallenge.usecase.ports;

import br.com.fiap.techchallenge.entity.Cliente;


public interface ClientePort {

    void cadastrarCliente(Cliente cliente);

    Cliente retornarCliente(String cpf);
}
