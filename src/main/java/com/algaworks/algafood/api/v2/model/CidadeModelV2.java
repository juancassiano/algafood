package com.algaworks.algafood.api.v2.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long idCidade;
    @ApiModelProperty(example = "Rio de Janeiro")
    private String nomeCidade;

    @ApiModelProperty(value = "ID do Estado", example = "1")
    private Long idEstado;
    @ApiModelProperty(example = "RJ")
    private String nomeEstado;
}
