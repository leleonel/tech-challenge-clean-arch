package br.com.fiap.techchallenge.entity;

import br.com.fiap.techchallenge.usecase.enums.CategoriaProdutoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    @NonNull
    CategoriaProdutoEnum categoria;
    @Column
    @NonNull
    String nome;
    @Column
    @NonNull
    String descricao;
    @Column
    @NonNull
    BigDecimal preco;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Pedido pedido;


    public Produto(Long id, CategoriaProdutoEnum categoria, String nome, String descricao, BigDecimal preco) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }


    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public CategoriaProdutoEnum getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setCategoria(@NonNull CategoriaProdutoEnum categoria) {
        this.categoria = categoria;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(@NonNull BigDecimal preco) {
        this.preco = preco;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
