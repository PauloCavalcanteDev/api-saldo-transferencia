# api-saldo-transferencia
Desafio engenharia de software
![arquitetura](https://github.com/PauloCavalcanteDev/api-saldo-transferencia/assets/162932967/f6f3cff2-e46d-4932-b400-5ee0e3a757da)

1- Escolhi utilizar uma arquitetura toda AWS , pela estabilidade, facilidade de implementação e priorizei serviços auto escaláveis através de configuração.

2- Coloquei um o Gateway para receber todas as conexão, assim conseguimos ter uma estabilidade, controle de configurações, gerência de cache, O API Gateway administra todas as tarefas envolvidas no recebimento e processamento de até centenas de milhares de chamadas de API simultâneas, casando bem com o nosso cenário.

3- Optei também por colocar um ECS com fargate configurado com auto scaling assim se adaptando a demanda e o cluster multi- az fornecendo uma tolerância maior a falhas.

4- O banco escolhido foi RDS Postgres, por ter ser altamente eficiente e escalável, armazenamento rápido, alta disponibilidade e a opção de utilizar réplicas de leitura trazendo agilidade para consulta de dados e consequentemente retorno mais rápido para API. 

5- Temos também uma um redis para armazenar o cache que se faz extremamente necessário para guardar informações que mudam com pouca frequência.

6-Uma fila SQS que recebe transferência que por algum motivo falharam na hora de envio para o BACEN, retornando através de um listener para ser enviado em sequência.

7- O Cloudwatch é o responsável por armazenar os logs, não se limitando a essa função,Onde criaremos dash’s para monitoramento , alrmes e envio de menagem para um tópico do SNS que por sua vez envia e-mail para a squad quando ocorrer alarmes,
desvio do padrão nos logs , exemplo excesso de retornos 429, exceptions acima da média e falhas em
comunicação com as bases de dados ou outras API”s.


# Para Rodar a aplicação:

<h4>Pré requisitos:</h4>
 - Docker
 - Java 17
 - maven 3.8 ou superior

Antes de iniciar é preciso subir a infra com o comando :
```
docker-compose up -d
```
<h4>Após:</h4>

```
mvn clean package
java -jar target/api-saldo-transferencia-0.0.1-SNAPSHOT.jar
```

# Endpoints:

<h3>Consulta Saldo:</h3>

```
GET: http://localhost:8080/api/v1/operacoes/consulta-saldo/{id_cliente}/{id_conta}

id_cliente - id_conta
10           100
20           200
30           300
40           400
50           500

```

<h3>Realizar transferencia:</h3>

```
POST: http://localhost:8080/api/v1/operacoes/transferencia

{
	"clienteSolicitante": id_cliente,
	"contaOrigem": id_conta,
	"contaDestino": id_conta_destino,
	"valor": XXX.XX
}

ex:
{
	"clienteSolicitante": 40,
	"contaOrigem": 400,
	"contaDestino": 200,
	"valor": 750
}

```