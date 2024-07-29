/*
package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.ProdutoRepository;
import br.com.fiap.techchallenge.usecase.enums.CategoriaProdutoEnum;
import br.com.fiap.techchallenge.entity.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoAdapterTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoAdapter produtoAdapter;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(CategoriaProdutoEnum.BEBIDA);
        produto.setDescricao("Descrição do produto teste");
        produto.setPreco(new BigDecimal("10.00"));
    }

    @Test
    void testCadastrarProduto() {
        when(repository.existsByNome(produto.getNome())).thenReturn(false);

        produtoAdapter.cadastrarProduto(produto);

        verify(repository, times(1)).save(produto);
    }

    @Test
    void testCadastrarProdutoExistente() {
        when(repository.existsByNome(produto.getNome())).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            produtoAdapter.cadastrarProduto(produto);
        });

        String expectedMessage = "Produto Produto Teste já existe na base de produtos cadastrados.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRetornarProdutoPorCategoria() {
        when(repository.findAllByCategoria(CategoriaProdutoEnum.BEBIDA)).thenReturn(List.of(produto));

        List<Produto> foundProduto = produtoAdapter.retornarProdutoPorCategoria("BEBIDA");

        assertEquals(produto, foundProduto);
    }

    @Test
    void testRemoverProduto() {
        when(repository.findByNome(produto.getNome())).thenReturn(produto);

        produtoAdapter.removerProduto(produto.getNome());

        verify(repository, times(1)).delete(produto);
    }

}
*/
