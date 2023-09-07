package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "pessoas")
public class Pessoa implements Serializable {

    @Id
    private UUID id;

//    @TextIndexed
    @Indexed(unique = true)
//    @NotNull
//    @Size(max = 100)
    private String apelido;

//    @TextIndexed
//    @NotNull
//    @Size(max = 200)
    private String nome;

//    @TextIndexed
//    @NotNull
    private String nascimento;

//    @TextIndexed
//    @NotNull
    private List<String> stack;

    @TextIndexed
    private String allFieldsInOne;

    public Pessoa() {
    }

    public Pessoa(String apelido, String nome, String nascimento, List<String> stack) {
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
        StringBuilder builder = new StringBuilder();
        builder.append(apelido);
        builder.append(nome);
        builder.append(apelido);
        builder.append(nascimento);
        if (Objects.nonNull(stack))
            for (String str : stack) {
            builder.append(str);
         }
        this.allFieldsInOne = builder.toString().trim();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    public String getAllFieldsInOne() {
        return allFieldsInOne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isValid() {

        if (apelido == null || apelido.length() > 32) {
            return false;
        }

        if (nome == null || nome.length() > 100) {
            return false;
        }

        if (!DateValidator.isDateValid(nascimento) ) {
            return false;
        }

        if (stack == null) {
            return false;
        }

        for (String item : stack) {
            if (item == null || item.length() > 32) {
                return false;
            }
        }

        return true;
    }

}