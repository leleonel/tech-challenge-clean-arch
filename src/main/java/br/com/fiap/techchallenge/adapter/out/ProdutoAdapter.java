package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.ProdutoRepository;
import br.com.fiap.techchallenge.usecase.enums.CategoriaProdutoEnum;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ports.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoAdapter implements ProdutoPort {

    @Autowired
    ProdutoRepository repository;

    @Override
    public void cadastrarProduto(Produto produto) {

        try {
            if (repository.existsByNome(produto.getNome())) {
                throw new RuntimeException(String.format("Produto %s já existe na base de produtos cadastrados.", produto.getNome()));
            }
            repository.save(produto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Produto> retornarProdutoPorCategoria(String categoria) {
        return repository.findAllByCategoria(CategoriaProdutoEnum.valueOf(categoria.toUpperCase()));
    }

    @Override
    public void removerProduto(String nome) {
        Produto produto = repository.findByNome(nome);
        repository.delete(produto);
    }

    @Override
    public void atualizarProduto(Produto produto) {

        //Busca produto a ser atualizado
        Produto produtoAtualizado = repository.findByNome(produto.getNome());

        //Cria novo produto para receber novas características
        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setCategoria(produto.getCategoria());
        produtoAtualizado.setDescricao(produto.getDescricao());
        produtoAtualizado.setPreco(produto.getPreco());

        repository.save(produtoAtualizado);
    }
}
