package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class EstadoModel extends RepresentationModel<EstadoModel> {
    @ApiModelProperty(value = "ID do Estado", example = "1")
    private Long id;
    @ApiModelProperty(example = "RJ")
    private String nome;
}
