package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import io.swagger.annotations.*;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @ApiOperation("Confirmar Pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido confirmado com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    void confirmar(@ApiParam(value = "Código do pedido", example = "7507075d-b84f-4362-ba30-b39f2835f49f") String codigoPedido);

    @ApiOperation("Cancelar Pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido cancelado com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    void cancelar(@ApiParam(value = "Código do pedido", example = "7507075d-b84f-4362-ba30-b39f2835f49f") String codigoPedido);

    @ApiOperation("Entregar Pedido")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Pedido entregue com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    void entregar(@ApiParam(value = "Código do pedido", example = "7507075d-b84f-4362-ba30-b39f2835f49f") String codigoPedido);

}
