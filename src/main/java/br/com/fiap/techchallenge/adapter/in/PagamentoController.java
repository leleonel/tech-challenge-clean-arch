package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.usecase.PagamentoUseCase;
import br.com.fiap.techchallenge.usecase.enums.StatusPagamentoEnum;
import com.google.common.math.Stats;
import com.google.zxing.WriterException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    PagamentoUseCase service;

    @GetMapping("/gerar_pagamento")
    public ResponseEntity<String> retornarPagamento(@QueryParam("id") Long id) throws MPException, IOException, WriterException {
        return ResponseEntity.ok().body(service.gerarQrCode(id));
    }

    @GetMapping("/status_pagamento")
    public ResponseEntity<Optional<StatusPagamentoEnum>> retornarStatusPagamento(@QueryParam("id") Long id) {

        return ResponseEntity.ok().body(service.retornarStatusPagamento(id));
    }

}
