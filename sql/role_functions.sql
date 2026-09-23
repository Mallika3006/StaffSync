-- 1. CREATE
CREATE OR REPLACE FUNCTION create_role(
    p_role_name VARCHAR,
    p_description VARCHAR
)
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
    INSERT INTO roles (role_name, description)
    VALUES (p_role_name, p_description)
    RETURNING
        roles.role_id,
        roles.role_name,
        roles.description;
END;
$$;


-- 2. GET ALL
CREATE OR REPLACE FUNCTION get_all_roles()
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    r.role_id,
    r.role_name,
    r.description
FROM roles r
ORDER BY r.role_id;
END;
$$;


-- 3. GET BY ID
CREATE OR REPLACE FUNCTION get_role_by_id(
    p_id INTEGER
)
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    r.role_id,
    r.role_name,
    r.description
FROM roles r
WHERE r.role_id = p_id;
END;
$$;


-- 4. UPDATE
CREATE OR REPLACE FUNCTION update_role(
    p_id INTEGER,
    p_role_name VARCHAR,
    p_description VARCHAR
)
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
UPDATE roles r
SET
    role_name = p_role_name,
    description = p_description
WHERE r.role_id = p_id
    RETURNING
        r.role_id,
        r.role_name,
        r.description;
END;
$$;


-- 5. DELETE
CREATE OR REPLACE FUNCTION delete_role(
    p_id INTEGER
)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM roles
WHERE role_id = p_id;

RETURN FOUND;
END;
$$;


-- 6. SEARCH BY ROLE NAME
CREATE OR REPLACE FUNCTION search_roles_by_name(
    p_role_name VARCHAR
)
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    r.role_id,
    r.role_name,
    r.description
FROM roles r
WHERE r.role_name ILIKE '%' || p_role_name || '%'
ORDER BY r.role_id;
END;
$$;


-- 7. EXACT ROLE NAME
CREATE OR REPLACE FUNCTION get_role_by_exact_name(
    p_role_name VARCHAR
)
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    r.role_id,
    r.role_name,
    r.description
FROM roles r
WHERE LOWER(r.role_name) = LOWER(p_role_name);
END;
$$;


-- 8. SORT
CREATE OR REPLACE FUNCTION sort_roles(
    p_field VARCHAR,
    p_direction VARCHAR
)
RETURNS TABLE (
    role_id INTEGER,
    role_name VARCHAR,
    description VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN

    IF LOWER(p_field) = 'roleid' AND LOWER(p_direction) = 'desc' THEN

        RETURN QUERY
SELECT r.role_id, r.role_name, r.description
FROM roles r
ORDER BY r.role_id DESC;

ELSIF LOWER(p_field) = 'roleid' THEN

        RETURN QUERY
SELECT r.role_id, r.role_name, r.description
FROM roles r
ORDER BY r.role_id ASC;

ELSIF LOWER(p_field) = 'rolename' AND LOWER(p_direction) = 'desc' THEN

        RETURN QUERY
SELECT r.role_id, r.role_name, r.description
FROM roles r
ORDER BY r.role_name DESC;

ELSIF LOWER(p_field) = 'rolename' THEN

        RETURN QUERY
SELECT r.role_id, r.role_name, r.description
FROM roles r
ORDER BY r.role_name ASC;

ELSIF LOWER(p_field) = 'description' AND LOWER(p_direction) = 'desc' THEN

        RETURN QUERY
SELECT r.role_id, r.role_name, r.description
FROM roles r
ORDER BY r.description DESC;

ELSE

        RETURN QUERY
SELECT r.role_id, r.role_name, r.description
FROM roles r
ORDER BY r.description ASC;

END IF;

END;
$$;