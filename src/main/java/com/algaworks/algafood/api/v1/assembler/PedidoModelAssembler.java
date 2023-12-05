package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler
        extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlgaLinks algalinks;
    @Autowired
    private AlgaSecurity algaSecurity;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        if(algaSecurity.podePesquisarPedidos()){
            pedidoModel.add(algalinks.linkToPedidos());
        }

        if(algaSecurity.podeGerenciarPedidos(pedido.getCodigo())){
            if(pedido.podeSerConfirmado()){
                pedidoModel.add(algalinks.linkToConfirmacaoPedido(pedidoModel.getCodigo(), "confirmar"));
            }
            if(pedido.podeSerCancelado()){
                pedidoModel.add(algalinks.linkToCancelamentoPedido(pedidoModel.getCodigo(), "cancelar"));
            }
            if(pedido.podeSerEntregue()){
                pedidoModel.add(algalinks.linkToEntregaPedido(pedidoModel.getCodigo(), "entregar"));
            }
        }

        if(algaSecurity.podeConsultarRestaurantes()){
            pedidoModel.getRestaurante().add(algalinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if(algaSecurity.podeConsultarUsuariosGruposPermissoes()){
            pedidoModel.getCliente().add(algalinks.linkToUsuario(pedido.getCliente().getId()));

        }

        if(algaSecurity.podeConsultarFormasPagamento()){
            pedidoModel.getFormaPagamento().add(algalinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
        }

        if(algaSecurity.podeConsultarCidades()){
            pedidoModel.getEnderecoEntrega().getCidade().add(algalinks.linkToCidade(pedido.getEnderecoEntrega()
                    .getCidade().getId()));
        }

        if(algaSecurity.podeConsultarRestaurantes()){
            pedidoModel.getItens().forEach(item -> {
                item.add(algalinks.linkToProduto(pedidoModel.getRestaurante().getId(),item.getProdutoId(),"produto"));
            });
        }

        return pedidoModel;
    }
}
