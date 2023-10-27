package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.controller.*;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler
        extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algalinks;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(algalinks.linkToPedidos());

        if(pedido.podeSerConfirmado()){
            pedidoModel.add(algalinks.linkToConfirmacaoPedido(pedidoModel.getCodigo(), "confirmar"));
        }
        if(pedido.podeSerCancelado()){
            pedidoModel.add(algalinks.linkToCancelamentoPedido(pedidoModel.getCodigo(), "cancelar"));
        }
        if(pedido.podeSerEntregue()){
            pedidoModel.add(algalinks.linkToEntregaPedido(pedidoModel.getCodigo(), "entregar"));
        }

        pedidoModel.getRestaurante().add(algalinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(algalinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoModel.getFormaPagamento().add(algalinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoModel.getEnderecoEntrega().getCidade().add(algalinks.linkToCidade(pedido.getEnderecoEntrega()
                .getCidade().getId()));

        pedidoModel.getItens().forEach(item -> {
            item.add(algalinks.linkToProduto(pedidoModel.getRestaurante().getId(),item.getProdutoId(),"produto"));
        });

        return pedidoModel;
    }
}
