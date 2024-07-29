package br.com.fiap.techchallenge.usecase;

import br.com.fiap.techchallenge.entity.Cliente;
import br.com.fiap.techchallenge.usecase.ports.ClientePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClienteUseCase {

    ClientePort port;


    public void cadastrarCliente(Cliente cliente) {

        try {
            port.cadastrarCliente(cliente);
        } catch (Exception e) {
            throw new RuntimeException("Cliente já cadastrado");
        }

    }

    public Cliente retornarCliente(String cpf) {
        return port.retornarCliente(cpf);
    }


}
