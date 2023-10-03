package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    @ApiModelProperty(example = "400",position = 1)
    private Integer status;
    @ApiModelProperty(example = "https://algafood.com.br/erro-negocio",position = 10)
    private String type;
    @ApiModelProperty(example = "Violação de regra de negócio",position = 20)
    private String title;
    @ApiModelProperty(example = "Não existe um cadastro de estado com codigo 1",position = 30)
    private String detail;
    @ApiModelProperty(example = "Não existe um cadastro de estado com codigo 1",position = 35)
    private String userMessage;
    @ApiModelProperty(example = "2021-11-14T00:12:13",position = 40)
    private OffsetDateTime timestamp;
    @ApiModelProperty(value="Objetos ou campos que geraram o erro",position = 50)
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {
        @ApiModelProperty(example = "Estado")
        private String name;
        @ApiModelProperty(example = "Estado não encontrado")
        private String userMessage;
    }
}
