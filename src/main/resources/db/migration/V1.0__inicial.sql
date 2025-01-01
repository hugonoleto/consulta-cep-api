CREATE TABLE consulta_cep_log (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    response JSONB,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(8) NOT NULL CHECK (tipo IN ('REQUEST', 'RESPONSE')),
    correlation_id VARCHAR(36) NOT NULL,
    status_code INTEGER
);