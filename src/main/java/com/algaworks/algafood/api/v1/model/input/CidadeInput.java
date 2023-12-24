package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
