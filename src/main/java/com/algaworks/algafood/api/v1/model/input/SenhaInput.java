package com.algaworks.algafood.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class SenhaInput {

    @Schema(example = "123", required = true)
    @NotBlank
    private String senhaAtual;

    @Schema(example = "123", required = true)
    private String novaSenha;
}
