package br.com.fiap.techchallenge.usecase.ports;

import br.com.fiap.techchallenge.usecase.enums.StatusPagamentoEnum;
import com.google.zxing.WriterException;
import com.mercadopago.exceptions.MPException;

import java.io.IOException;
import java.util.Optional;

public interface PagamentoPort {
    String criarQRCode(Long id)throws MPException, WriterException, IOException;
    Optional<StatusPagamentoEnum> retornarStatusPagamento(Long id);
}
