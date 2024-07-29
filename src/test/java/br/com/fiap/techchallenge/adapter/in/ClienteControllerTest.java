package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.entity.Cliente;
import br.com.fiap.techchallenge.usecase.ClienteUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteUseCase service;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678901");
        cliente.setEmail("joao.silva@example.com");
    }

    @Test
    void testCadastrar() throws Exception {
        Mockito.doNothing().when(service).cadastrarCliente(any(Cliente.class));

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente cadastrado com sucesso!"));

        Mockito.verify(service, Mockito.times(1)).cadastrarCliente(any(Cliente.class));
    }

    @Test
    void testRetornarClienteCadastrado() throws Exception {
        Mockito.when(service.retornarCliente(anyString())).thenReturn(cliente);

        mockMvc.perform(get("/clientes/buscar_cliente")
                        .queryParam("cpf", "12345678901"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Dados do cliente retornados com sucesso")));

        Mockito.verify(service, Mockito.times(1)).retornarCliente(anyString());
    }
}
