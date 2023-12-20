package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;


@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {
    @Operation(summary = "Cadastra uma nova cidade", responses = {
            @ApiResponse(responseCode = "201", description = "Cidade cadastrada"),

    })
    @PostMapping
    CidadeModel adicionar(
            @RequestBody(description = "Representação de uma nova cidade", required = true)
            CidadeInput cidadeInput);

    @Operation(description = "Atualiza uma cidade por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cidade atualizada", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "400", description = "ID da Cidade inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
    })
    CidadeModel atualizar(
            @Parameter(description = "ID de uma cidade")
            Long cidadeId,
            @RequestBody(description = "Representação de uma cidade com novos dados", required = true)
            CidadeInput cidadeInput);


    @Operation(summary = "Lista as cidades")
    CollectionModel<CidadeModel> listar();

    @Operation(summary = "Busca uma cidade pelo ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da Cidade inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(ref = "Problema")))
    })
     CidadeModel buscar(@Parameter(description = "ID de uma cidade")Long cidadeId);

    @Operation(summary = "Remove uma cidade por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Cidade excluída", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(ref = "Problema")))

    })
     void remover(@Parameter(description = "ID de uma cidade", required = true) Long cidadeId);
}
