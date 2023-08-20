package com.hugomarques.rinhabackend2023.pessoas;

 public class PessoaNotFoundException extends RuntimeException {
        PessoaNotFoundException(String id) {
            super("Pessoa com id: " + id + " não encontrada.");
        }
    }

