package com.algaworks.algafood.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInputV2 {

    @Schema(example = "Brasileira", required = true)
    @NotBlank
    private String nomeCozinha;
}
