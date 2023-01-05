package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar(){

        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
        Estado estado = estadoRepository.buscar(estadoId);

        if(estado != null){
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
        Estado estadoAtual = cadastroEstadoService.salvar(estado);

        if(estadoAtual != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if(estadoAtual != null){
            BeanUtils.copyProperties(estado, estadoAtual, "id");
            cadastroEstadoService.salvar(estadoAtual);
            return ResponseEntity.ok().body(estadoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId){
        try{
            cadastroEstadoService.excluir(estadoId);

            return ResponseEntity.noContent().build();
        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
