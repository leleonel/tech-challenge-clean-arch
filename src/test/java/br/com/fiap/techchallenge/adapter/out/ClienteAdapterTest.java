package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.ClienteRepository;
import br.com.fiap.techchallenge.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteAdapterTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteAdapter clienteAdapter;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setCpf("12345678900");
        // configure outros campos de Cliente conforme necessário
    }

    @Test
    void testCadastrarCliente() {
        clienteAdapter.cadastrarCliente(cliente);
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void testRetornarCliente() {
        when(repository.findByCpf("12345678900")).thenReturn(cliente);
        Cliente result = clienteAdapter.retornarCliente("12345678900");
        assertEquals(cliente, result);
        verify(repository, times(1)).findByCpf("12345678900");
    }
}
