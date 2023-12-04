package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.input.GrupoInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/grupos")
public class GrupoController implements GrupoControllerOpenApi {
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GrupoModel> listar(){
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionModel(todosGrupos);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GrupoModel buscar(@PathVariable Long grupoId){
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return grupoModelAssembler.toModel(grupo);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput){
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        grupo = cadastroGrupoService.salvar(grupo);
        return grupoModelAssembler.toModel(grupo);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping(path = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput){
        Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = cadastroGrupoService.salvar(grupoAtual);

        return grupoModelAssembler.toModel(grupoAtual);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long grupoId){
        cadastroGrupoService.excluir(grupoId);
    }

}
