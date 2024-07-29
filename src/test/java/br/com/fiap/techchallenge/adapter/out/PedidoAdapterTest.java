package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.PedidoRepository;
import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
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
class PedidoAdapterTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoAdapter pedidoAdapter;

    private Pedido pedido;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        produto1 = new Produto();
        produto1.setPreco(new BigDecimal("50.00"));

        produto2 = new Produto();
        produto2.setPreco(new BigDecimal("100.00"));

        pedido = new Pedido();
        pedido.setProdutos(Arrays.asList(produto1, produto2));
        pedido.setStatus(StatusPedidoEnum.RECEBIDO);
    }

    @Test
    void testCriarPedido() {
        pedidoAdapter.criarPedido(Arrays.asList(produto1, produto2));

        verify(repository, times(1)).save(any(Pedido.class));
    }

    @Test
    void testAtualizarPedido() {
        when(repository.findByIdWithProdutos(1L)).thenReturn(pedido);

        pedidoAdapter.atualizarPedido(1L, StatusPedidoEnum.RECEBIDO);

        verify(repository, times(1)).findByIdWithProdutos(1L);
        verify(repository, times(1)).save(pedido);
        assertEquals(StatusPedidoEnum.RECEBIDO, pedido.getStatus());
    }

    @Test
    void testRetornarPedidos() {
        when(repository.findAll()).thenReturn(Arrays.asList(pedido));

        List<Pedido> pedidos = pedidoAdapter.retornarPedidos();

        assertNotNull(pedidos);
        assertFalse(pedidos.isEmpty());
        verify(repository, times(1)).findAll();
    }
}
