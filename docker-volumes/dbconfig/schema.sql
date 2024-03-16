
CREATE TABLE IF NOT EXISTS CONTAS (
    conta_id bigint PRIMARY KEY,
    cliente_id bigint not null,
    limite_diario numeric(1000,2),
    saldo  numeric(1000,2),
   ativa BOOLEAN NOT NULL
);
 CREATE INDEX IF NOT EXISTS contas ON CONTAS (conta_id, cliente_id);


CREATE TABLE IF NOT EXISTS TRANSACOES (
    id_transacao bigint  PRIMARY KEY not null,
    conta_orig bigint ,
    conta_dstn bigint ,
    cliente_id bigint,
    tipo_trsc varchar(20),
    valor numeric(1000,2),
    status_bacen varchar(15),
    data_hora TIMESTAMP NOT NULL DEFAULT NOW()
);



    INSERT INTO CONTAS (conta_id, cliente_id , limite_diario, saldo, ativa)
           VALUES (100, 10,1000.00 , 2000.00, TRUE),
                  (200, 20,1000.00 , 2000.00,TRUE),
                  (300, 30,1000.00 , 2000.00,FALSE),
                  (400, 40,1000.00 , 2100.00,FALSE),
                  (500, 50,1000.00 , 3000.00,TRUE),
                  (600, 60,1000.00 , 1200.00,TRUE),
                  (700, 70,1000.00 , 4000.00,TRUE),
                  (800, 80,1000.00 , 3000.00,FALSE),
                  (900, 90,1000.00 , 3000.00,TRUE),
                  (1000,100,1000.00 , 3000.00,TRUE),
                  (2000, 200,1000.00 , 3000.00,TRUE);
         
            --   (2, 2, 0, 800 * 100),
            --   (3, 3, 0, 10000 * 100),
            --   (4, 4, 0, 100000 * 100),
            --   (5, 5, 0, 5000 * 100);
