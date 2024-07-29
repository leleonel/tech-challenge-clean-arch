package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoUseCase service;

    @PostMapping("/cadastrar_produto")
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto) {

        try {
            service.cadastrarProduto(produto);
            return ResponseEntity.ok().body("Produto cadastrado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(String.format("Produto já cadastrado na base de dados de produtos."));
        }

    }

    @GetMapping(value = "/buscar_produto")
    public ResponseEntity<List<Produto>> retornarProdutoCadastrado(@QueryParam("categoria") String categoria){

        try {
            List<Produto> produto = service.retornarProdutoPorCategoria(categoria);
            return ResponseEntity.ok().body(produto);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

    }

    @DeleteMapping(value = "/{nome}/remover_produto")
    public ResponseEntity<String> removerProduto(@PathVariable("nome") String nome){

        try {
            service.removerProduto(nome);
            return ResponseEntity.ok().body(String.format("Produto %s removido da base de produtos com sucesso!", nome));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Não existe este produto na base de dados para ser removido.");
        }
    }

    @PutMapping(value = "/atualizar_produto")
    public ResponseEntity<String> atualizarProduto(@RequestBody Produto produto){

        try {
            service.atualizarProduto(produto);
            return ResponseEntity.ok().body(String.format("Produto %s atualizado na base de produtos com sucesso!", produto.getNome()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Não foi possível atualizar o produto. Certifique-se de que ele exista na base de daos e que todas as informações estão sendo enviadas..");
        }
    }



}
