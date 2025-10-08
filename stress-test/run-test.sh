# Exemplos de requests
# curl -v -XPOST -H "content-type: application/json" -d '{"apelido" : "xpto", "nome" : "xpto xpto", "nascimento" : "2000-01-01", "stack": null}' "http://localhost:9999/pessoas"
# curl -v -XGET "http://localhost:9999/pessoas/1"
# curl -v -XGET "http://localhost:9999/pessoas?t=xpto"
# curl -v "http://localhost:9999/contagem-pessoas"


# coloque o diretório do Gatling
GATLING_BIN_DIR=$HOME/opt/gatling/3.9.5/bin

# diretório do projeto
WORKSPACE=$HOME/proj/rinha-backend-2023-q3-java/stress-test

sh $GATLING_BIN_DIR/gatling.sh -rm local -rd "DESCRICAO" -rf $WORKSPACE/user-files/results -sf $WORKSPACE/user-files/simulations/rinhabackend  -rsf $WORKSPACE/user-files/resources

sleep 10

curl -v "http://localhost:9999/contagem-pessoas"
