package br.com.fiap.techchallenge.adapter.out.repository;

import br.com.fiap.techchallenge.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
