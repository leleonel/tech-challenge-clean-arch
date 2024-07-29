package br.com.fiap.techchallenge.entity;

import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Column
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    @NonNull
    String cpf;
    @Column
    @NonNull
    String nome;
    @Column
    @NonNull
    String email;

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Cliente() {
    }

    public @NonNull String getCpf() {
        return cpf;
    }

    public @NonNull String getNome() {
        return nome;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public void setCpf(@NonNull String cpf) {
        this.cpf = cpf;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
