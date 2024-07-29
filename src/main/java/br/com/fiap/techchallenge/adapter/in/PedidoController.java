package br.com.fiap.techchallenge.adapter.in;

import br.com.fiap.techchallenge.usecase.enums.StatusPedidoEnum;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.PedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoUseCase service;


    @PostMapping("/cadastrar_pedidos")
    public ResponseEntity<String> cadastrar(@RequestBody List<Produto> produtosPedido) throws Exception {

        try {
            service.criarPedido(produtosPedido);

            return ResponseEntity.ok().body("Pedido efetuado com sucesso!");
        } catch (Exception e){
            throw new Exception("Houve um erro ao cadastrar pedido, " + e);

        }

    }

    @GetMapping("/atualizar_pedidos")
    public ResponseEntity<String> atualizarPedido(@QueryParam("id") Long id,
                                                  @QueryParam("status") String status) throws Exception {

        try{
            service.atualizarPedido(id, StatusPedidoEnum.valueOf(status.toUpperCase()));
            return ResponseEntity.ok().body("Status do pedido atualizado com sucesso!");
        }catch (Exception e){
            throw new Exception("Houve um erro ao atualizar status do pedido, " + e);
        }
    }

    @GetMapping("/buscar_pedidos")
    public ResponseEntity<List<Pedido>> retornarPedidos() throws Exception {

        try{
            List<Pedido> pedidos = service.listarPedidos();
            return ResponseEntity.ok().body(pedidos);
        }catch (Exception e){
            throw new Exception("Houve um erro ao retornar pedidos, " + e);
        }
    }
}
