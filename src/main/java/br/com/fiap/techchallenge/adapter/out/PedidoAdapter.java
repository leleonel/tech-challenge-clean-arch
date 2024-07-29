package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.PedidoRepository;
import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ports.PedidoPort;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PedidoAdapter implements PedidoPort {

    @Autowired
    PedidoRepository repository;

    @Override
    public void criarPedido(List<Produto> produtos) {

        Pedido pedido = new Pedido();
        pedido.setProdutos(produtos);
        pedido.setStatus(StatusPedidoEnum.RECEBIDO);

        repository.save(pedido);
    }

    @Override
    public void atualizarPedido(Long id, StatusPedidoEnum status) {

        try {

            Pedido pedido = repository.findByIdWithProdutos(id);
            pedido.setStatus(status);
            repository.save(pedido);

        }catch (Exception e){
            throw new RuntimeException("Não foi possível atualizar pedido" + e);
        }
    }

    @Override
    public List<Pedido> retornarPedidos() {

        try{
            List<Pedido> pedidos = repository.findAll();

            //Define a ordem dos status
            Map<StatusPedidoEnum, Integer> ordem = new HashMap<>();
            ordem.put(StatusPedidoEnum.PRONTO, 1);
            ordem.put(StatusPedidoEnum.EM_PREPARACAO, 2);
            ordem.put(StatusPedidoEnum.RECEBIDO, 3);

            // Excluir retorno de pedidos com status FINALIZADO;
            StatusPedidoEnum statusExcluidoLista = StatusPedidoEnum.FINALIZADO;

            return pedidos.stream()
                    .filter(p -> !statusExcluidoLista.equals(p.getStatus()))
                    .distinct()
                    .sorted(Comparator.comparing(p -> ordem.getOrDefault(p.getStatus(), Integer.MAX_VALUE)))
                    .collect(Collectors.toList());

        }catch (Exception e){
            throw new RuntimeException("Não foi possível retornar lista de pedidos." + e);
        }
    }
}
