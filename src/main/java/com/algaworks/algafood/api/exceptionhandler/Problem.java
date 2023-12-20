package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Schema(name = "Problema")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    @Schema(example = "400")
    private Integer status;
    @Schema(example = "https://algafood.com.br/erro-negocio")
    private String type;
    @Schema(example = "Violação de regra de negócio")
    private String title;
    @Schema(example = "Não existe um cadastro de estado com codigo 1")
    private String detail;
    @Schema(example = "Não existe um cadastro de estado com codigo 1")
    private String userMessage;
    @Schema(example = "2021-11-14T00:12:13")
    private OffsetDateTime timestamp;
    @Schema(description="Objetos ou campos que geraram o erro")
    private List<Object> objects;

    @Schema(name = "ObjetoProblema")
    @Getter
    @Builder
    public static class Object {
        @Schema(example = "Estado")
        private String name;
        @Schema(example = "Estado não encontrado")
        private String userMessage;
    }
}
