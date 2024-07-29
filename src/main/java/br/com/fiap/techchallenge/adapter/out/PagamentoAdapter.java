package br.com.fiap.techchallenge.adapter.out;

import br.com.fiap.techchallenge.adapter.out.repository.PedidoRepository;
import br.com.fiap.techchallenge.entity.Pedido;
import br.com.fiap.techchallenge.entity.Produto;
import br.com.fiap.techchallenge.usecase.enums.StatusPagamentoEnum;
import br.com.fiap.techchallenge.usecase.ports.PagamentoPort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
public class PagamentoAdapter implements PagamentoPort {


    private String token = "TEST-5944175036124517-052521-3feef8b9a1e4ed3c27383a8c9b53af67-212697704";

    @Autowired
    PedidoRepository repository;


    public String criarQRCode(Long id) throws MPException, WriterException, IOException {
        //TODO -> buscar valor da soma dos produtos via repository e criar chamada do QRCode.

        MercadoPago.SDK.setAccessToken(token);

        Pedido pedido = repository.findByIdWithProdutos(id);

        List<Produto> produtos = pedido.getProdutos();
        BigDecimal totalPagamento = BigDecimal.ZERO;

        for (Produto produto : produtos){

            BigDecimal preco = produto.getPreco();
            totalPagamento = totalPagamento.add(preco);
        }
        Preference preference = new Preference();
        Item item = new Item();
        item.setTitle("Combo pedido")
                .setQuantity(1)
                .setUnitPrice(Float.valueOf(String.valueOf(totalPagamento)));

        preference.appendItem(item);

        preference.save();

        //Gera QRCode a partir do link de pagamento
        String linkPagamento = preference.getSandboxInitPoint();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(linkPagamento, BarcodeFormat.QR_CODE, 200,200);

        ByteArrayOutputStream pngOutStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutStream);
        byte[] pngData = pngOutStream.toByteArray();
        String base64QRCode = Base64.getEncoder().encodeToString(pngData);


        return base64QRCode;
    }

    @Override
    public Optional<StatusPagamentoEnum> retornarStatusPagamento(Long id) {

        Pedido pedido = repository.findByIdWithProdutos(id);
        return Optional.ofNullable(pedido.getStatusPagamentoEnum());

    }

}
