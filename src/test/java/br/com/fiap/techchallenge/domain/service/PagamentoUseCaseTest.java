package br.com.fiap.techchallenge.domain.service;

import br.com.fiap.techchallenge.usecase.ports.PagamentoPort;
import br.com.fiap.techchallenge.usecase.PagamentoUseCase;
import com.google.zxing.WriterException;
import com.mercadopago.exceptions.MPException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoUseCaseTest {

    @Mock
    private PagamentoPort port;

    @InjectMocks
    private PagamentoUseCase pagamentoUseCase;

    private final Long pedidoId = 1L;
    private final String qrCode = "fakeQRCode";

    @Test
    void testGerarQrCode() throws MPException, IOException, WriterException {
        when(port.criarQRCode(pedidoId)).thenReturn(qrCode);

        String result = pagamentoUseCase.gerarQrCode(pedidoId);

        assertEquals(qrCode, result);
        verify(port, times(1)).criarQRCode(pedidoId);
    }
}
