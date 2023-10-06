package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Juan Cassiano")
    private String nome;

    @ApiModelProperty(example = "juancassiano@hotmail.com")
    private String email;
}
