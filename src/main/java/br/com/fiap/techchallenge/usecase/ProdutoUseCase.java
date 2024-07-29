package br.com.fiap.techchallenge.usecase;

import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ports.ProdutoPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProdutoUseCase  {

    ProdutoPort port;

    public void cadastrarProduto(Produto produto) {
        port.cadastrarProduto(produto);

    }


    public List<Produto> retornarProdutoPorCategoria(String categoria) {
        return port.retornarProdutoPorCategoria(categoria);
    }


    public void removerProduto(String nome) {
        port.removerProduto(nome);
    }

    public void atualizarProduto(Produto produto) {
        port.atualizarProduto(produto);
    }

}
