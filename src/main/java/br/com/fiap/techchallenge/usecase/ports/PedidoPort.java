package br.com.fiap.techchallenge.usecase.ports;

import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;

import java.util.List;

public interface PedidoPort {

    void criarPedido(List<Produto> pedido);
    void atualizarPedido(Long id, StatusPedidoEnum status);
    List<Pedido> retornarPedidos();

}
