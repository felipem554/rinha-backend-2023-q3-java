package com.hugomarques.rinhabackend2023.pessoas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
public class PessoaQueryController {

    private final Logger log = LoggerFactory.getLogger(PessoaQueryController.class);

    private final PessoaRepository repository;

    public PessoaQueryController(PessoaRepository repository) {
        this.repository = repository;
    }

    /**
     * 200 for OK
     * 404 for not found.
     */
    @GetMapping("/pessoas/{id}")
    public Mono<ResponseEntity<Pessoa>>getById(@PathVariable UUID id) {

        if (Cache.temNosIDs(id.toString())) {
            return Mono.just(ResponseEntity.ok(  Cache.getPessoa(id.toString())));
        }

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build()) ;
    }

    @GetMapping("/pessoas")
    public Mono<ResponseEntity<List<Pessoa>>> findAllBySearchTerm(@RequestParam(required=true,name= "t") String term) {
        if (term == null || term.isEmpty() || term.isBlank()) {
            return Mono.just(ResponseEntity.badRequest().build());
        }
        log.info("Busca por:  {}",term);

        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(term);
        Flux<Pessoa> pessoasFlux = repository.findAllBy(criteria).take(5);

        return pessoasFlux.collectList().map(ResponseEntity::ok);
    }

    @GetMapping("/contagem-pessoas")
    public Mono<ResponseEntity<String>> count() {

        // uso do cache
        log.error("cacheLocalPorId = "+Cache.cacheLocalPorId.size());
        log.error("cacheLocalDeApelidos = "+Cache.cacheLocalDeApelidos.size());

        // uso de erros
        log.error("httpMessageNotReadableException =  "+PessoaExceptionHandler.httpMessageNotReadableException);
        log.error("missingServletRequestParameterException = "+ PessoaExceptionHandler.missingServletRequestParameterException);

        return repository.count()
                .map(count -> ResponseEntity.ok(String.valueOf(count)));
    }

}