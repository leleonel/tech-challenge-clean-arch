package br.com.fiap.techchallenge.usecase;

import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ports.PedidoPort;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class PedidoUseCase  {


    PedidoPort port;

    public void criarPedido(List<Produto> produtos) {
        port.criarPedido(produtos);
    }

    public void atualizarPedido(Long id, StatusPedidoEnum status)  {
        port.atualizarPedido(id, status);
    }

    public List<Pedido> listarPedidos() {
        return port.retornarPedidos();
    }

}
