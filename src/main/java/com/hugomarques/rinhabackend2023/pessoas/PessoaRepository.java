package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface PessoaRepository extends ReactiveCrudRepository<Pessoa, UUID> {

    Flux<Pessoa> findAllBy(TextCriteria criteria);

    Mono<Pessoa> findByApelido(String apelido);

}