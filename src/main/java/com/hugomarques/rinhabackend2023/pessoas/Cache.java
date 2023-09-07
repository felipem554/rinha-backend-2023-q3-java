package com.hugomarques.rinhabackend2023.pessoas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Cache {

    public static final Map<String,Pessoa> cacheLocalPorId = new HashMap<>(50000);
    public static final Set<String> cacheLocalDeApelidos = new HashSet<>(50000);

    public static boolean temNosApelidos ( String apelido) {
        return cacheLocalDeApelidos.contains(apelido);
    }

    public static boolean temNosIDs (String id) {
        return cacheLocalPorId.containsKey(id);
    }

    public static void add(Pessoa pessoa) {
        cacheLocalPorId.put(pessoa.getId().toString(),pessoa);
        cacheLocalDeApelidos.add(pessoa.getApelido());
    }

    public static Pessoa getPessoa(String id) {
        return cacheLocalPorId.get(id);
    }

}