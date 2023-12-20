package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.core.openapi.PageableParameter;
import com.algaworks.algafood.core.openapi.SearchPedidoParameter;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Pedidos")
 public interface PedidoControllerOpenApi {

    @PageableParameter
    @SearchPedidoParameter
    @Operation(summary = "Pesquisa os pedidos")
    PagedModel<PedidoResumoModel> pesquisar(@Parameter(hidden = true) Pageable pageable,
                                            @Parameter(hidden = true) PedidoFilter filtro);


    @Operation(summary = "Busca um pedido por código", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))
            }),
    })
     PedidoModel buscar(@Parameter(description = "Código do pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002",
            required = true) String codigoPedido);

    @Operation(summary = "Registra um pedido", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),

    })
     PedidoModel adicionar( @RequestBody(description = "Representação de um novo pedido") PedidoInput pedidoInput);

}
