package com.hugomarques.rinhabackend2023.pessoas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
public class PessoaNewController {

    private final Logger log = LoggerFactory.getLogger(PessoaNewController.class);

    private final PessoaRepository repository;

    public PessoaNewController(PessoaRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns 201 for success and 422 if there's already a person with that same nickname.
     * 400 for invalid requests.
     */
    @PostMapping("/pessoas")
    public Mono<ResponseEntity<Object>> newPessoa(@RequestBody Pessoa pessoa) {

       // entrada valida ?
       // existe no cache de apelidos ?
        if (!pessoa.isValid() ||  Cache.temNosApelidos(pessoa.getApelido())) {
            return Mono.just(ResponseEntity.unprocessableEntity().build());
        }

            return repository.findByApelido(pessoa.getApelido())
                    .flatMap(existingPessoa -> {

                        // coloca no cache
                        Cache.add(existingPessoa);

                        return Mono.just(ResponseEntity.unprocessableEntity().build());

                    })
                    .switchIfEmpty(Mono.defer(() -> {

                        // Caso não exista no banco de dados, grava no cache e no banco de dados
                        pessoa.setId(UUID.randomUUID());
                        return repository.save(pessoa)
                                .flatMap(savedPessoa -> {

                                    log.info("Pessoa com apelido {} adicionada  no banco de dados/cache.", savedPessoa.getApelido());
                                    // coloca no cache
                                    Cache.add(savedPessoa);

                                    return Mono.just(ResponseEntity.created(URI.create("/pessoas/"+savedPessoa.getId().toString()) ).build());
                                });
                    }));
    }

}