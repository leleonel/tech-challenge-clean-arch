/*
package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.usecase.PagamentoUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagamentoController.class)
class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoUseCase service;

    private final String qrCode = "fakeQRCode";


    @Test
    void testRetornarPagamento() throws Exception {
        Mockito.when(service.gerarQrCode(anyLong())).thenReturn(qrCode);

        mockMvc.perform(get("/gerar_pagamento/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(qrCode));

        Mockito.verify(service, Mockito.times(1)).gerarQrCode(anyLong());
    }
}
*/
