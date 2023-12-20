package com.algaworks.algafood.api.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

    @Schema(description = "ID da cidade", example = "1")
    private Long idCidade;
    @Schema(example = "Rio de Janeiro")
    private String nomeCidade;

    @Schema(description = "ID do Estado", example = "1")
    private Long idEstado;
    @Schema(example = "RJ")
    private String nomeEstado;
}
