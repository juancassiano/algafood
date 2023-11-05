package com.algaworks.algafood.api.v2.controller;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.core.web.AlgaMediaTypes;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeControllerV2 {

    @Autowired
    private CidadeModelAssemblerV2 cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassembler;


    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CollectionModel<CidadeModelV2> listar(){
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);
//        cidadesCollectionModel.add(linkTo(CidadeController.class).withSelfRel());
    }

    @GetMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CidadeModelV2 buscar(@PathVariable Long cidadeId){

        Cidade cidade =  cadastroCidadeService.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);

    }

    @PostMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput){

        try{
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
            cidade = cadastroCidadeService.salvar(cidade);
            CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());

            return cidadeModel;

        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping(path = "/{cidadeId}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
    public CidadeModelV2 atualizar(@PathVariable Long cidadeId,
                                 @Valid @RequestBody CidadeInputV2 cidadeInput){
        try{
            Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);

        }catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }

    }

    //  Não pode ser mapeado na mesma URL em um MediaType diferente, já que não aceita entrada e retorna void.
//    @DeleteMapping("/{cidadeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long cidadeId) {
//        cadastroCidadeService.excluir(cidadeId);
//    }

}
