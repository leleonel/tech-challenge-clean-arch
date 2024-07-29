package br.com.fiap.techchallenge.domain.service;

import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.usecase.PedidoUseCase;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ports.PedidoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoUseCaseTest {

    @Mock
    private PedidoPort port;

    @InjectMocks
    private PedidoUseCase pedidoUseCase;

    private Produto produto1;
    private Produto produto2;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setPreco(new BigDecimal("50.00"));

        produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setPreco(new BigDecimal("100.00"));

        pedido = new Pedido();
        pedido.setProdutos(Arrays.asList(produto1, produto2));
        pedido.setStatus(StatusPedidoEnum.RECEBIDO);
    }

    @Test
    void testCriarPedido() {
        List<Produto> produtos = Arrays.asList(produto1, produto2);

        pedidoUseCase.criarPedido(produtos);

        verify(port, times(1)).criarPedido(produtos);
    }

    @Test
    void testAtualizarPedido() {
        pedidoUseCase.atualizarPedido(1L, StatusPedidoEnum.RECEBIDO);

        verify(port, times(1)).atualizarPedido(1L, StatusPedidoEnum.RECEBIDO);
    }

    @Test
    void testListarPedidos() {
        when(port.retornarPedidos()).thenReturn(Arrays.asList(pedido));

        List<Pedido> pedidos = pedidoUseCase.listarPedidos();

        assertNotNull(pedidos);
        assertFalse(pedidos.isEmpty());
        assertEquals(1, pedidos.size());
        assertEquals(pedido, pedidos.get(0));
        verify(port, times(1)).retornarPedidos();
    }
}
