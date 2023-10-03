package com.algaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @ApiModelProperty(example = "Rio de Janeiro")
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "1")
    @NotNull
    @Valid
    private EstadoIdInput estado;
}
