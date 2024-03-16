# api-saldo-transferencia
Desafio engenharia de software
![arquitetura](https://github.com/PauloCavalcanteDev/api-saldo-transferencia/assets/162932967/f6f3cff2-e46d-4932-b400-5ee0e3a757da)

Escolhi utilizar uma arquitetura toda AWS , pela estabilidade, facilidade de implementação e priorizei serviços auto escaláveis através de configuração.
Coloquei um o Gateway para receber todas as conexão, assim conseguimos ter uma estabilidade, controle de configurações, gerência de cache, O API Gateway administra todas as tarefas envolvidas no recebimento e processamento de até centenas de milhares de chamadas de API simultâneas, casando bem com o nosso cenário.
Optei também por colocar um ECS com fargate configurado com auto scaling assim adaptando a demanda e o cluster multi- az fornecendo uma tolerância maior a falhas.
O banco escolhido foi RDS Postgres, por ter ser altamente eficiente escalável, armazenamento rápido, alta disponibilidade e a opção de utilizar réplicas de leitura trazendo agilidade para consulta de dados e consequentemente retorno mais rápido para API. Temos também uma um redis para armazenar o cache que se faz extremamente necessário para guardar informações que mudam com pouca frequência. Uma fila SQS que recebe transferência que por algum motivo falharam na hora de envio para o BACEN, retornando através de um listener para ser enviado em sequência.
O Cloudwatch é o responsável por armazenar os logs, não se limitando a essa função,Onde criaremos dash’s para monitoramento , alrmes e envio de menagem para um tópico do SMS que por sua vez envia e-mail para a squad quando ocorrer alarmes , desvio do padrão nos logs , exemplo excesso de retornos 429, exceptions acima da média e falhas em
comunicação com as bases de dados ou outras API”s.
