

CREATE TABLE IF NOT EXISTS CLIENTES (

	cliente_id bigint  PRIMARY KEY not null,
    nome varchar(36),
	conta_id bigint NOT NULL

);
CREATE INDEX IF NOT EXISTS cliente_id ON CLIENTES (cliente_id);

CREATE TABLE IF NOT EXISTS CONTAS (
    conta_id bigint PRIMARY KEY,
    cliente_id bigint not null,
    limite numeric(1000,2),
    saldo  numeric(1000,2)
);
CREATE INDEX IF NOT EXISTS contas ON CONTAS (conta_id, cliente_id);


CREATE TABLE IF NOT EXISTS TRANSACOES (
    id_transacao bigint  PRIMARY KEY not null,
    conta_id bigint ,
    cliente_id bigint,
    tipo varchar(20),
    valor numeric(1000,2),
    data_hora TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS transacao ON TRANSACOES (conta_id, cliente_id, id_transacao);
--
--
--

--INSERT INTO saldos (id, cliente_id, valor, limite)
--        VALUES (1, 1, 0, 1000 * 100),
--               (2, 2, 0, 800 * 100),
--               (3, 3, 0, 10000 * 100),
--               (4, 4, 0, 100000 * 100),
--               (5, 5, 0, 5000 * 100);
