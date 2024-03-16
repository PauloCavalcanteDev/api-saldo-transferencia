
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
                  (200, 11,1000.00 , 2000.00,TRUE),
                  (300, 12,1000.00 , 2000.00,TRUE),
                  (400, 13,1000.00 , 2100.00,FALSE),
                  (500, 14,1000.00 , 3000.00,FALSE),
                  (600, 15,1000.00 , 1200.00,TRUE),
                  (700, 16,1000.00 , 4000.00,TRUE),
                  (800, 17,1000.00 , 3000.00,FALSE),
                  (900, 18,1000.00 , 3000.00,TRUE),
                  (1001,19,1000.00 , 3000.00,TRUE),
                  (850, 20,1000.00 , 3000.00,TRUE);
         
            --   (2, 2, 0, 800 * 100),
            --   (3, 3, 0, 10000 * 100),
            --   (4, 4, 0, 100000 * 100),
            --   (5, 5, 0, 5000 * 100);
