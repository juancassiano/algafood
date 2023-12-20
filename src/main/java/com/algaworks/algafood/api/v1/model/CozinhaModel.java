package com.algaworks.algafood.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaModel extends RepresentationModel<CozinhaModel> {
    @Schema(example = "1")
//    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @Schema(example = "Brasileira")
//    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
