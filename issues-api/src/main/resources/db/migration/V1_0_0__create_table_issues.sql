DROP TABLE IF EXISTS issues;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE issues
(
    id          UUID        NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    title       TEXT        NOT NULL,
    description TEXT        NOT NULL,
    priority    VARCHAR(10) NOT NULL,
    "type"      VARCHAR(10) NOT NULL,
    assignee_id UUID        NULL,
    reporter_id UUID        NOT NULL,
    team_id     UUID        NULL,
    created_at  TIMESTAMP   NOT NULL
);

CREATE INDEX idx_issues_id ON issues (id);
CREATE INDEX idx_issues_assignee_id ON issues (assignee_id);
CREATE INDEX idx_issues_reporter_id ON issues (reporter_id);
CREATE INDEX idx_issues_team_id ON issues (team_id);
CREATE INDEX idx_issues_created_at ON issues (created_at);