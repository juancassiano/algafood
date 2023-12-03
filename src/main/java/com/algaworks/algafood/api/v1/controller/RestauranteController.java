package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.RestauranteApenasNomeModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteBasicoModelAssembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.api.v1.model.input.RestauranteInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/v1/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @Autowired
    private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

    @Autowired
    private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;

    @Autowired
    private SmartValidator validator;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestauranteBasicoModel> listar() {
        return restauranteBasicoModelAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
        return restauranteApenasNomeModelAssembler.toCollectionModel(
                restauranteRepository.findall()
        );
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteModel buscar(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput){
        try{
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
        }catch(RestauranteNaoEncontradoException | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteModel atualizar(@PathVariable Long restauranteId, @Valid @RequestBody RestauranteInput restauranteInput){
        Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

        try{
            return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId){
        cadastroRestauranteService.ativar(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId){
        cadastroRestauranteService.inativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos( @RequestBody List<Long> restauranteIds){
        cadastroRestauranteService.ativar(restauranteIds);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos( @RequestBody List<Long> restauranteIds){
        cadastroRestauranteService.inativar(restauranteIds);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId){
        cadastroRestauranteService.abrir(restauranteId);
        return ResponseEntity.noContent().build();
    }
    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId){
        cadastroRestauranteService.fechar(restauranteId);
        return ResponseEntity.noContent().build();
    }


    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante,objectName);

        validator.validate(restaurante, bindingResult);

        if(bindingResult.hasErrors()){
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino,
                       HttpServletRequest request){
        ServletServerHttpRequest serverHttpRequest =  new ServletServerHttpRequest(request);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        }catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

}
