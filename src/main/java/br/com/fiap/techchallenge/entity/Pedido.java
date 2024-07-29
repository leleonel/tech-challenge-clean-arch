package br.com.fiap.techchallenge.entity;

import br.com.fiap.techchallenge.usecase.enums.StatusPagamentoEnum;
import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NonNull
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Produto> produtos;
    @Column
    private StatusPedidoEnum status;

    public StatusPagamentoEnum getStatusPagamentoEnum() {
        return statusPagamentoEnum;
    }

    public void setStatusPagamentoEnum(StatusPagamentoEnum statusPagamentoEnum) {
        this.statusPagamentoEnum = statusPagamentoEnum;
    }

    @Column
    private StatusPagamentoEnum statusPagamentoEnum;

    public Pedido(Long id, @NonNull List<Produto> produtos, StatusPedidoEnum status) {
        this.id = id;
        this.produtos = produtos;
        this.status = status;
    }

    public Pedido(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NonNull List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(@NonNull List<Produto> pedido) {
        this.produtos = pedido;
        for (Produto produto : pedido){
            produto.setPedido(this);
        }
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }
}
