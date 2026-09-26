-- =========================================================
-- TEAM FUNCTIONS
-- =========================================================


-- 1. CREATE TEAM
CREATE OR REPLACE FUNCTION create_team(
    p_team_name VARCHAR,
    p_description VARCHAR,
    p_department_id INTEGER
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO teams (
    team_name,
    description,
    department_id
)
VALUES (
           p_team_name,
           p_description,
           p_department_id
       );

RETURN QUERY
SELECT *
FROM teams
WHERE team_id = currval(
        pg_get_serial_sequence('teams', 'team_id')
                );
END;
$$;


-- 2. GET ALL TEAMS
CREATE OR REPLACE FUNCTION get_all_teams()
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM teams
ORDER BY team_id;
END;
$$;


-- 3. GET TEAM BY ID
CREATE OR REPLACE FUNCTION get_team_by_id(
    p_team_id INTEGER
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM teams
WHERE team_id = p_team_id;
END;
$$;


-- 4. UPDATE TEAM
CREATE OR REPLACE FUNCTION update_team(
    p_team_id INTEGER,
    p_team_name VARCHAR,
    p_description VARCHAR,
    p_department_id INTEGER
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE teams
SET
    team_name = p_team_name,
    description = p_description,
    department_id = p_department_id
WHERE team_id = p_team_id;

RETURN QUERY
SELECT *
FROM teams
WHERE team_id = p_team_id;
END;
$$;


-- 5. DELETE TEAM
CREATE OR REPLACE FUNCTION delete_team(
    p_team_id INTEGER
)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM teams
WHERE team_id = p_team_id;

RETURN FOUND;
END;
$$;


-- 6. GET TEAM BY EXACT NAME
CREATE OR REPLACE FUNCTION get_team_by_name(
    p_team_name VARCHAR
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM teams
WHERE LOWER(team_name) = LOWER(p_team_name);
END;
$$;


-- 7. SEARCH TEAMS BY NAME
CREATE OR REPLACE FUNCTION search_teams_by_name(
    p_team_name VARCHAR
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM teams
WHERE team_name ILIKE '%' || p_team_name || '%'
ORDER BY team_id;
END;
$$;


-- 8. GET TEAMS BY DEPARTMENT
CREATE OR REPLACE FUNCTION get_teams_by_department(
    p_department_id INTEGER
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM teams
WHERE department_id = p_department_id
ORDER BY team_id;
END;
$$;


-- 9. GET TEAM MEMBERS BY EMPLOYEE ID
CREATE OR REPLACE FUNCTION get_team_members_by_employee(
    p_employee_id INTEGER
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT e.*
FROM employees e
WHERE e.team_id = (
    SELECT team_id
    FROM employees
    WHERE employee_id = p_employee_id
)
ORDER BY e.employee_id;
END;
$$;


--get team by employees
CREATE OR REPLACE FUNCTION get_team_by_employee(
    p_employee_id INTEGER
)
RETURNS SETOF teams
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT t.*
FROM teams t
         JOIN employees e
              ON e.team_id = t.team_id
WHERE e.employee_id = p_employee_id;
END;
$$;