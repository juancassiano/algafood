package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.model.PermissaoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissoes")
public interface PermissaoControllerOpenApi {
    @ApiOperation("Lista as permissoes")
    CollectionModel<PermissaoModel> listar();
}
