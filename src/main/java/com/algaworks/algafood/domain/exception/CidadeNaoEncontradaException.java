package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String mensagem) {

        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long cidadeId){
        super(String.format("Não existe um cadastro de cidade com o código %d", cidadeId));
    }
}
