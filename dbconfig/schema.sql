
CREATE TABLE IF NOT EXISTS CONTAS (
    conta_id bigint PRIMARY KEY,
    cliente_id bigint not null,
    limite_diario numeric(1000,2),
    saldo  numeric(1000,2)
);
-- CREATE INDEX IF NOT EXISTS contas ON CONTAS (conta_id, cliente_id);


CREATE TABLE IF NOT EXISTS TRANSACOES (
    id_transacao bigint  PRIMARY KEY not null,
    conta_id bigint ,
    cliente_id bigint,
    tipo varchar(20),
    valor numeric(1000,2),
    status_bacen varchar(15),
    data_hora TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS transacao ON TRANSACOES (conta_id, cliente_id, id_transacao);
--
--
--

INSERT INTO CONTAS (conta_id, cliente_id , limite_diario, saldo)
       VALUES (10, 10,1000.00 , 2000.00),
              (20, 11,1000.00 , 2000.00),
              (30, 12,1000.00 , 2000.00),
              (40, 13,1000.00 , 2100.00),
              (50, 14,1000.00 , 3000.00),
              (60, 15,1000.00 , 1200.00),
              (70, 16,1000.00 , 4000.00),
              (80, 17,1000.00 , 3000.00),
              (90, 18,1000.00 , 3000.00),
              (100,19,1000.00 , 3000.00),
              (85, 20,1000.00 , 3000.00);
         
            --   (2, 2, 0, 800 * 100),
            --   (3, 3, 0, 10000 * 100),
            --   (4, 4, 0, 100000 * 100),
            --   (5, 5, 0, 5000 * 100);
