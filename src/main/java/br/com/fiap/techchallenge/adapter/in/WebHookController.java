package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.config.WebHookConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/send")
public class WebHookController {

    private final WebHookConfig webHookConfig;

    @Autowired
    public WebHookController(WebHookConfig config){
        this.webHookConfig = config;
    }

    @PostMapping("/registra_webhook")
    public Mono<ResponseEntity<String>> enviaWebHook(@RequestBody Map<String, Object> payload) {
        return webHookConfig.enviaWebHook(payload)
                .map(response ->
                        ResponseEntity.ok("Url cadastrada no Webhook: " + response))
                .defaultIfEmpty(ResponseEntity.status(500).
                        body("Falha ao enviar webhook"));
    }

    @PostMapping("/validacao_pagamento")
    public Mono<ResponseEntity<String>> enviaWebHookValidacaoPgto(@RequestBody Map<String, Object> payload) {
        return webHookConfig.enviaWebHookValidacaoPgto(payload)
                .map(response ->
                        ResponseEntity.ok("Status de pagamento: " + response))
                .defaultIfEmpty(ResponseEntity.status(500).
                        body("Falha ao enviar webhook"));
    }

}
