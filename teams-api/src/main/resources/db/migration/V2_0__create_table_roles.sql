DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id   UUID         NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

CREATE INDEX idx_roles_id ON roles (id);

ALTER TABLE roles ADD CONSTRAINT uk_roles_name UNIQUE (name);