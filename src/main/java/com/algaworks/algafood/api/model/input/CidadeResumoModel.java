package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.api.model.EstadoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

    private Long id;
    private String nome;
    private String estado;
}
