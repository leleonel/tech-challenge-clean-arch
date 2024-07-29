package br.com.fiap.techchallenge.config;

import br.com.fiap.techchallenge.usecase.ports.ClientePort;
import br.com.fiap.techchallenge.usecase.ports.PagamentoPort;
import br.com.fiap.techchallenge.usecase.ports.PedidoPort;
import br.com.fiap.techchallenge.usecase.ports.ProdutoPort;
import br.com.fiap.techchallenge.usecase.ClienteUseCase;
import br.com.fiap.techchallenge.usecase.PagamentoUseCase;
import br.com.fiap.techchallenge.usecase.PedidoUseCase;
import br.com.fiap.techchallenge.usecase.ProdutoUseCase;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Beans {


    @Bean
    ClienteUseCase service(ClientePort port){
        return new ClienteUseCase(port);
    }

    @Bean
    ProdutoUseCase produtoService(ProdutoPort port){
        return new ProdutoUseCase(port);
    }

    @Bean
    PedidoUseCase pedidoService(PedidoPort port){return new PedidoUseCase(port);}

    @Bean
    PagamentoUseCase pagamentoService(PagamentoPort port){return new PagamentoUseCase(port);}

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Tech Challenge Fiap Lanchonete")
                        .version("1.0.0")
                        .description("API para pedido de autoatendimento para lanchonete, " +
                                "utilizando Spring Boot, Jpa e arquitetura Hexagonal."));
    }


    @Bean
    public WebHookConfig webHookConfig(WebClient.Builder webClient){
        return new WebHookConfig(webClient);
    }


}
