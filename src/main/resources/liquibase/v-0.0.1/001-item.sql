--liquibase formatted sql
--changeset id:1588510956469-1
CREATE TABLE IF NOT EXISTS item (
    id        UUID                    NOT NULL CONSTRAINT PK_item PRIMARY KEY DEFAULT uuid_generate_v4(),
    name      VARCHAR UNIQUE          NOT NULL,
    added     DATE DEFAULT NOW()      NOT NULL,
    available BOOLEAN   DEFAULT TRUE  NOT NULL
);