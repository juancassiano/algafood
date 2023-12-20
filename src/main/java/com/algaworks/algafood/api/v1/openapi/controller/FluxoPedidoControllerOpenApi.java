package com.algaworks.algafood.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface FluxoPedidoControllerOpenApi {

    @Operation(summary = "Confirmar Pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido confirmado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(ref = "Problema")))

    })
    ResponseEntity<Void> confirmar(@Parameter(description = "Código do pedido", example = "7507075d-b84f-4362-ba30-b39f2835f49f", required = true) String codigoPedido);

    @Operation(summary = "Cancelar Pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(ref = "Problema")))

    })
    ResponseEntity<Void> cancelar(@Parameter(description = "Código do pedido", example = "7507075d-b84f-4362-ba30-b39f2835f49f", required = true) String codigoPedido);

    @Operation(summary = "Entregar Pedido", responses = {
            @ApiResponse(responseCode = "204", description = "Pedido entregue com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(ref = "Problema")))

    })
    ResponseEntity<Void> entregar(@Parameter(description = "Código do pedido", example = "7507075d-b84f-4362-ba30-b39f2835f49f", required = true) String codigoPedido);

}
