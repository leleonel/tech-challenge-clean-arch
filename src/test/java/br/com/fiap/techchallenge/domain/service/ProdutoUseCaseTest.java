package br.com.fiap.techchallenge.domain.service;

import br.com.fiap.techchallenge.usecase.enums.CategoriaProdutoEnum;
import br.com.fiap.techchallenge.usecase.ProdutoUseCase;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ports.ProdutoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoUseCaseTest {

    @Mock
    private ProdutoPort port;

    @InjectMocks
    private ProdutoUseCase produtoUseCase;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(CategoriaProdutoEnum.LANCHE);
        produto.setDescricao("Descrição do Produto Teste");
        produto.setPreco(new BigDecimal("100.00"));
    }

    @Test
    void testCadastrarProduto() {
        produtoUseCase.cadastrarProduto(produto);

        verify(port, times(1)).cadastrarProduto(produto);
    }

    @Test
    void testRetornarProdutoPorCategoria() {
        when(port.retornarProdutoPorCategoria("CATEGORIA_TESTE")).thenReturn((List.of(produto)));

        List<Produto> result = produtoUseCase.retornarProdutoPorCategoria("CATEGORIA_TESTE");

        assertEquals(List.of(produto), result);
        verify(port, times(1)).retornarProdutoPorCategoria("CATEGORIA_TESTE");
    }

    @Test
    void testRemoverProduto() {
        produtoUseCase.removerProduto("Produto Teste");

        verify(port, times(1)).removerProduto("Produto Teste");
    }

    @Test
    void testAtualizarProduto() {
        produtoUseCase.atualizarProduto(produto);

        verify(port, times(1)).atualizarProduto(produto);
    }
}
