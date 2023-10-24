package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Juan Cassiano")
    private String nome;

    @ApiModelProperty(example = "juancassiano@hotmail.com")
    private String email;
}
