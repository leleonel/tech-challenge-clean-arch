package br.com.fiap.techchallenge.usecase;

import br.com.fiap.techchallenge.usecase.enums.StatusPagamentoEnum;
import br.com.fiap.techchallenge.usecase.ports.PagamentoPort;
import com.google.zxing.WriterException;
import com.mercadopago.exceptions.MPException;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
public class PagamentoUseCase {

    PagamentoPort port;

    public String gerarQrCode(Long id) throws MPException, IOException, WriterException {
        return port.criarQRCode(id);
    }

    public Optional<StatusPagamentoEnum> retornarStatusPagamento (Long id) {

        return port.retornarStatusPagamento(id);

    }
}
