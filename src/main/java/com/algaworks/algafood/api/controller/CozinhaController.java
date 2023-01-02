package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
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
            cozinhaRepository.salvar(cozinhaAtual);
//            return ResponseEntity.ok(cozinhaAtual);
            return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtual);
        }
//        return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
            Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
            if (cozinha != null) {
                cozinhaRepository.remover(cozinha);

//        return ResponseEntity.noContent().build();
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

//        return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
