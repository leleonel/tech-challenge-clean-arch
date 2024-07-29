package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.PedidoRepository;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import com.google.zxing.WriterException;
import com.mercadopago.exceptions.MPException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoAdapterTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PagamentoAdapter pagamentoAdapter;

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
    }

    @Test
    void testCriarQRCode() throws MPException, WriterException, IOException {
        when(repository.findByIdWithProdutos(1L)).thenReturn(pedido);

        String qrCode = pagamentoAdapter.criarQRCode(1L);

        assertNotNull(qrCode);
        verify(repository, times(1)).findByIdWithProdutos(1L);

        byte[] decodedBytes = Base64.getDecoder().decode(qrCode);
        assertNotNull(decodedBytes);
    }
}
