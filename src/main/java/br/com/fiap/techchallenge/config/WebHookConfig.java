package br.com.fiap.techchallenge.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class WebHookConfig {

    private final WebClient webClient;

    @Autowired
    public WebHookConfig(WebClient.Builder webClient){
        this.webClient = webClient.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> enviaWebHook(Map<String, Object> payload){
        return webClient.post()
                .uri("/registra_webhook")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> enviaWebHookValidacaoPgto(Map<String, Object> payload) {
        return webClient.post()
                .uri("/validacao_pagamento_webhook")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class);
    }




}
