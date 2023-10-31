package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;


@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @ApiOperation("Lista os usuários responsáveis associados ao restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    CollectionModel<UsuarioModel> listar(@ApiParam(value = "ID do Restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Dessassocia os usuários responsáveis do restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do Restaurante", example = "1", required = true)Long restauranteId,
                     @ApiParam(value = "ID do usuário", example = "1", required = true)
                     Long usuarioId);


    @ApiOperation("Associa os usuários responsáveis do restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do Restaurante", example = "1", required = true) Long restauranteId,
                  @ApiParam(value = "ID do usuário", example = "1", required = true)
                  Long usuarioId);
}
