DROP TABLE IF EXISTS teams;

CREATE TABLE teams (
     id UUID NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
     name VARCHAR(150) NOT NULL
);

CREATE INDEX idx_teams_id ON teams (id);

ALTER TABLE teams ADD CONSTRAINT uk_teams_name UNIQUE (name);
