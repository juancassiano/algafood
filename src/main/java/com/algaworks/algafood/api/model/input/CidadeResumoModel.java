package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.api.model.EstadoModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Duque de Caxias")
    private String nome;
    @ApiModelProperty(example = "Rio de Janeiro")
    private String estado;
}
