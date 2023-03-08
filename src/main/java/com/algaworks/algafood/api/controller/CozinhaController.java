package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping()
    public List<CozinhaModel> listar() {

       List<Cozinha> todasCozinhas = cozinhaRepository.findAll();

       return cozinhaModelAssembler.toCollectionModel(todasCozinhas);

    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {

        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
            Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
            cozinha = cadastroCozinhaService.salvar(cozinha);

            return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInput cozinhaInput) {
            Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
            cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
            cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

            return cozinhaModelAssembler.toModel(cozinhaAtual);

    }


    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
               cadastroCozinhaService.excluir(cozinhaId);
    }
}
