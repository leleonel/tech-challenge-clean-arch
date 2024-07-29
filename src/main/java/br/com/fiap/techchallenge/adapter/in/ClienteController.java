package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.entity.Cliente;
import br.com.fiap.techchallenge.usecase.ClienteUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteUseCase service;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Cliente dadosCliente) throws Exception {

        try{
            service.cadastrarCliente(dadosCliente);
            return ResponseEntity.ok().body("Cliente cadastrado com sucesso!");
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (Exception e){
            throw new Exception("Houve um erro ao cadastrar cliente, " + e);

        }


    }

    @GetMapping(value = "/buscar_cliente")
    public ResponseEntity<String> retornarClienteCadastrado(@QueryParam("cpf") String cpf) throws Exception {
        try{
            Cliente cliente = service.retornarCliente(cpf);
            if (cliente == null){
                return ResponseEntity.notFound().build();
            }
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cliente);
            return ResponseEntity.ok().body("Dados do cliente retornados com sucesso: " + json);
        }catch (Exception e){
            throw new Exception("Houve um erro ao retornar cliente cadastrado, " + e);
        }
    }

}
