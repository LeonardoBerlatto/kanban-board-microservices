INSERT INTO issues (title, description, status, priority, "type", assignee_id, reporter_id, team_id, created_at)
VALUES ('Issue 1', 'Description 1', 'OPEN', 'HIGH', 'BUG', uuid_generate_v1(), uuid_generate_v1(), uuid_generate_v1(), '2019-01-01 00:00:00');

INSERT INTO issues (title, description, status, priority, "type", assignee_id, reporter_id, team_id, created_at)
VALUES ('Issue 2', 'Description 2', 'OPEN', 'BUG', 'BUG', uuid_generate_v1(), uuid_generate_v1(), uuid_generate_v1(), '2019-01-01 00:00:00');