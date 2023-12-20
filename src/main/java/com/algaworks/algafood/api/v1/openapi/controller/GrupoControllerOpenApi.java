package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.ResponseBody;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
 public interface GrupoControllerOpenApi {
    @Operation(summary = "Busca um grupo pelo ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
    })
    GrupoModel buscar(@Parameter(description = "ID do grupo")Long grupoId);


    @Operation(summary = "Lista os grupos")
    CollectionModel<GrupoModel> listar();

    @Operation(summary = "Cadastra um grupo", responses = {
            @ApiResponse(responseCode = "201", description = "Grupo cadastrado"),

    })
     GrupoModel adicionar(@RequestBody(description = "Representação de um novo Grupo") GrupoInput grupoInput);

    @Operation(summary = "Atualiza um grupo pelo ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "Problema")))
    })
     GrupoModel atualizar(@Parameter(description = "ID do grupo") Long grupoId,
                                @RequestBody(description = "Representação de um novo Grupo")
                                GrupoInput grupoInput);
    @Operation(summary = "Deleta um grupo pelo ID", responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "Problema")))

    })
     void remove(@Parameter(description = "ID do grupo") Long grupoId);

}
