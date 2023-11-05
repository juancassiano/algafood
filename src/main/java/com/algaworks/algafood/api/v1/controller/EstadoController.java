package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.input.EstadoInput;
import com.algaworks.algafood.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EstadoModel> listar(){

        List<Estado> todosEstados = estadoRepository.findAll();

        return estadoModelAssembler.toCollectionModel(todosEstados);
    }

    @GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoModel buscar(@PathVariable Long estadoId){

        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput){
            Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

            estado = cadastroEstadoService.salvar(estado);

            return estadoModelAssembler.toModel(estado);

    }

    @PutMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody EstadoInput estadoInput){
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
        estadoAtual = cadastroEstadoService.salvar(estadoAtual);

        return estadoModelAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId){
        cadastroEstadoService.excluir(estadoId);
    }
}
