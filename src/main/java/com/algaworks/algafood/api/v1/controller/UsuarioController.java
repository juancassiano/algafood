package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;
import com.algaworks.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;
    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UsuarioModel> listar(){
        List<Usuario> todosUsuarios = usuarioRepository.findAll();
        return usuarioModelAssembler.toCollectionModel(todosUsuarios);
    }

    @GetMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioModel buscar(@PathVariable Long usuarioId){
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput){
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenhaInput);
        usuario = cadastroUsuarioService.salvar(usuario);

        return usuarioModelAssembler.toModel(usuario);
    }

    @PutMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput){
        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuarioService.salvar(usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput){
        cadastroUsuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
    }
}
