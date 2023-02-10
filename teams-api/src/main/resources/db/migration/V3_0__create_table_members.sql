DROP TABLE IF EXISTS members;

CREATE TABLE members(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL,
    team_id UUID NOT NULL,
    CONSTRAINT pk_membership PRIMARY KEY (user_id, team_id),
    CONSTRAINT fk_member_role FOREIGN KEY (role_id) REFERENCES roles (id),
    constraint fk_member_team FOREIGN KEY (team_id) REFERENCES teams (id)
);

CREATE INDEX idx_memberships_team_id_user_id ON members (user_id, team_id);