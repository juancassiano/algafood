package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    public List<CidadeModel> listar();

    @ApiOperation("Busca uma cidade pelo ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da Cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    public CidadeModel buscar(@ApiParam(value = "ID de uma cidade")Long cidadeId);

    @ApiOperation("Cadastra uma nova cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada"),
    })
    @PostMapping
    public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade") Long cidadeId,
                                 @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados")
                                 CidadeInput cidadeInput);

    @ApiOperation("Remove uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    public void remover(@ApiParam(value = "ID de uma cidade") Long cidadeId);
}
