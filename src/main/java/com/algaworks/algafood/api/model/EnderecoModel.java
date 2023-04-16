package com.algaworks.algafood.api.model;

import com.algaworks.algafood.api.model.input.CidadeResumoModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {

    private String cep;
    private String logradouro;
    private String numero;
    private String comlemento;
    private String bairro;
    private CidadeResumoModel cidade;
}
