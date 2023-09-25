package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido){
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        pedido.confirmar();
        var mensagem = EnvioEmailService.Mensagem.builder()
                        .assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
                        .corpo("pedido-confirmado.html")
                        .variavel("pedido", pedido)
                        .destinatario(pedido.getCliente().getEmail())
                        .build();
        envioEmailService.enviar(mensagem);
    }

    @Transactional
    public void entregar(String codigoPedido){
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido){
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        pedido.cancelar();
    }
}


//confirmado pra entregue
//criado para cancelado