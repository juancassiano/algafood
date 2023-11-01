package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões associadas a um grupo")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "ID do grupo não encontrado", response = Problem.class)
    })
    CollectionModel<PermissaoModel> listar(@ApiParam(value = "ID do grupo", example = "1", required = true)
                                 Long grupoId);

    @ApiOperation("Dessassocia permissão de um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Dessassociação realizada com sucesso", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo ou Permissão não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do grupo", example = "1", required = true)
                     Long grupoId,
                               @ApiParam(value = "ID da permissão", example = "1", required = true)
                     Long permissaoId);


    @ApiOperation("Associa permissão de um grupo")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo ou Permissão não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> associar( @ApiParam(value = "ID do grupo", example = "1", required = true)
                   Long grupoId,
                   @ApiParam(value = "ID da permissão", example = "1", required = true)
                   Long permissaoId);
}
