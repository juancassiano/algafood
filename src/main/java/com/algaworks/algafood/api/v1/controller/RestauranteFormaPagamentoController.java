package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId){

        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<FormaPagamentoModel> formasPagamentoModel =  formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento())
                .removeLinks()
                .add(algaLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId,"associar"))
                .add(algaLinks.linkToRestauranteFormasPagamento(restauranteId));

        formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
            formasPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
                    restauranteId,formaPagamentoModel.getId(),"desassociar"
            ));
        });

        return formasPagamentoModel;
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId){
        cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();

    }

}
