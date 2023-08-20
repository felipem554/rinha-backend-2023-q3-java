package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface PessoaRepository extends ReactiveCrudRepository<Pessoa, String> {

    Flux<Pessoa> findAllBy(TextCriteria criteria);

}