package com.hugomarques.rinhabackend2023.exception;

 public class PessoaNotFoundException extends RuntimeException {
        public PessoaNotFoundException(String id) {
            super("Pessoa com id: " + id + " não encontrada.");
        }
    }

