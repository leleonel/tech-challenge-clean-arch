package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.PedidoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoUseCase service;

    private ObjectMapper objectMapper = new ObjectMapper();

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
    void testRetornarPedidos() throws Exception {
        Mockito.when(service.listarPedidos()).thenReturn(Arrays.asList(pedido));

        mockMvc.perform(get("/pedidos/buscar_pedidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(pedido))));

        Mockito.verify(service, Mockito.times(1)).listarPedidos();
    }
}
