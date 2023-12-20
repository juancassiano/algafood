package com.algaworks.algafood.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInputV2 {

    @Schema(example = "Rio de Janeiro", required = true)
    @NotBlank
    private String nomeCidade;

    @Schema(example = "1", required = true)
    @NotNull
    private Long idEstado;

}
