package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.ClienteRepository;
import br.com.fiap.techchallenge.entity.Cliente;
import br.com.fiap.techchallenge.usecase.ports.ClientePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClienteAdapter implements ClientePort {

    @Autowired
    ClienteRepository repository;

    @Override
    public void cadastrarCliente(Cliente cliente) {

        if(repository.existsByCpf(cliente.getCpf())){
            throw new RuntimeException("Cliente já cadastrado.");
        }
        repository.save(cliente);
    }

    @Override
    public Cliente retornarCliente(String cpf) {

        return repository.findByCpf(cpf);
    }
}
