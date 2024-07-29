package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.usecase.enums.CategoriaProdutoEnum;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ProdutoUseCase;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoUseCase service;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(CategoriaProdutoEnum.BEBIDA);
        produto.setDescricao("Descrição do Produto Teste");
        produto.setPreco(new BigDecimal("100.00"));
    }

    @Test
    void testCadastrarProduto() throws Exception {
        Mockito.doNothing().when(service).cadastrarProduto(any(Produto.class));

        mockMvc.perform(post("/produtos/cadastrar_produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto cadastrado com sucesso!"));

        Mockito.verify(service, Mockito.times(1)).cadastrarProduto(any(Produto.class));
    }


    @Test
    void testRemoverProduto() throws Exception {
        Mockito.doNothing().when(service).removerProduto(anyString());

        mockMvc.perform(delete("/produtos/Produto Teste/remover_produto"))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto Produto Teste removido da base de produtos com sucesso!"));

        Mockito.verify(service, Mockito.times(1)).removerProduto(anyString());
    }

    @Test
    void testAtualizarProduto() throws Exception {
        Mockito.doNothing().when(service).atualizarProduto(any(Produto.class));

        mockMvc.perform(put("/produtos/atualizar_produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto Produto Teste atualizado na base de produtos com sucesso!"));

        Mockito.verify(service, Mockito.times(1)).atualizarProduto(any(Produto.class));
    }
}
