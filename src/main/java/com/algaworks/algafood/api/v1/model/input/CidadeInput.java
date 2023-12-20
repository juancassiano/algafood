package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @Schema(example = "Rio de Janeiro", required = true)
    @NotBlank
    private String nome;

    @NotNull
    @Valid
    private EstadoIdInput estado;
}
