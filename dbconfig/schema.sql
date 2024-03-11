

CREATE TABLE IF NOT EXISTS clientes (

	cliente_id SERIAL PRIMARY KEY,
	conta INTEGER NOT NULL

);

CREATE TABLE IF NOT EXISTS CONTAS (
    conta_id SERIAL PRIMARY KEY,
    cliente_id SERIAL,
    limite numeric(1000,2),
    saldo  numeric(1000,2)
);

CREATE INDEX IF NOT EXISTS cliente_id ON CONTAS (cliente_id);


CREATE TABLE IF NOT EXISTS TRANSACOES (
    id_transacao SERIAL PRIMARY KEY,
    conta_id SERIAL ,
    cliente_id SERIAL,
    tipo varchar(20),
    valor numeric(1000,2),
    data_hora TIMESTAMP NOT NULL DEFAULT NOW()
)



--INSERT INTO saldos (id, cliente_id, valor, limite)
--        VALUES (1, 1, 0, 1000 * 100),
--               (2, 2, 0, 800 * 100),
--               (3, 3, 0, 10000 * 100),
--               (4, 4, 0, 100000 * 100),
--               (5, 5, 0, 5000 * 100);
