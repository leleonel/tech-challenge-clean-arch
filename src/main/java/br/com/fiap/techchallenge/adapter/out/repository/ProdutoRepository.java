package br.com.fiap.techchallenge.adapter.out.repository;

import br.com.fiap.techchallenge.usecase.enums.CategoriaProdutoEnum;
import br.com.fiap.techchallenge.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByCategoria(CategoriaProdutoEnum categoria);
    Produto findByNome(String nome);
    boolean existsByNome(String nome);

}
