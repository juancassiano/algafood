package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Grupos")
 public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    CollectionModel<GrupoModel> listar();

    @ApiOperation("Busca um grupo pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
     GrupoModel buscar(@ApiParam(value = "ID do grupo")Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado"),
    })
     GrupoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo Grupo") GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo pelo ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
     GrupoModel atualizar(@ApiParam(value = "ID do grupo") Long grupoId,
                                @ApiParam(name = "corpo", value = "Representação de um novo Grupo")
                                GrupoInput grupoInput);
    @ApiOperation("Deleta um grupo pelo ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo excluído", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
     void remove(@ApiParam(value = "ID do grupo") Long grupoId);

}
