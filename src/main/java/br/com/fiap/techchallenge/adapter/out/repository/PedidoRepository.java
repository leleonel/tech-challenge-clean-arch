package br.com.fiap.techchallenge.adapter.out.repository;

import br.com.fiap.techchallenge.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos")
    List<Pedido> findAll();

    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.produtos WHERE p.id =:id")
    Pedido findByIdWithProdutos(Long id);

}
