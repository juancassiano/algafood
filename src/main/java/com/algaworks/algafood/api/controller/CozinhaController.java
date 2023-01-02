package com.algaworks.algafood.api.controller;

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

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping()
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();

    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {

        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//            return ResponseEntity.ok(cozinha);
        }
        //        return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping()
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cadastroCozinha.salvar(cozinha);

        if (cozinhaAtual != null) {
//            return ResponseEntity.created(cozinhaAtual);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaAtual);
        }
        //        return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
//        cozinhaAtual.setNome(cozinha.getNome());
        if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cadastroCozinha.salvar(cozinhaAtual);
//            return ResponseEntity.ok(cozinhaAtual);
            return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
        }
//        return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
                cadastroCozinha.excluir(cozinhaId);
//        return ResponseEntity.noContent().build();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            }catch (EntidadeNaoEncontradaException e){
//        return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
