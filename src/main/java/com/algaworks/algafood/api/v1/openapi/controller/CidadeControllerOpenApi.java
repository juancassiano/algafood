package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;


@Api(tags = "Cidades")
 public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    CollectionModel<CidadeModel> listar();

    @ApiOperation("Busca uma cidade pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da Cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
     CidadeModel buscar(@ApiParam(value = "ID de uma cidade")Long cidadeId);

    @ApiOperation("Cadastra uma nova cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada"),
    })
    @PostMapping
     CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
     CidadeModel atualizar(@ApiParam(value = "ID de uma cidade") Long cidadeId,
                                 @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados")
                                 CidadeInput cidadeInput);

    @ApiOperation("Remove uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
     void remover(@ApiParam(value = "ID de uma cidade") Long cidadeId);
}
