# rinha-backend-2023-q3-java

Backend pra rinha backend 2023 q3

Projeto fork de   [https://github.com/hugomarques/rinha-backend-2023-q3-java](https://github.com/hugomarques/rinha-backend-2023-q3-java)

## Implementação


## Versão 1

A adaptação feita foi:

- trocar o PostgreSQL por MongoDB Reactive
- trocar o Spring MVC pelo  Spring WebFlux
- utilizar o Full Text Search do MongoDB na classe domain (@TextIndexed  cria o índice automaticamente)

## Versão 2

- removido Virtual Threads
- removido Redis (ou qqer cache)
- usado Spring Native (com GraalVM)

## Requisitos

Para rodar, precisamos ter instalado:

* docker
* Gatling (versão usada [3.9.5](https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.9.5/)


## Para rodar

1. Subir o ambiente (MongoDB + Redis) com o docker :

Na raiz do projeto, rodar: 

``  docker compose -f docker-compose-local.yml up  ``

2. Subir a aplicação do Spring Boot (pela IDE ou por terminal)

``  MAVEN_OPTS="-Xmx1500m --enable-preview"  mvn spring-boot:run ``

3. Rode os testes do Galting

``  
cd stress-test
./run-tests.sh
   ``

Tudo isso dá para ser feito dentro do IntelliJ tb se preferir.

A cada teste remova os volumes criados pelo Docker. 
