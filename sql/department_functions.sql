-- =========================================================
-- DEPARTMENT FUNCTIONS
-- =========================================================


-- 1. CREATE DEPARTMENT
CREATE OR REPLACE FUNCTION create_department(
    p_department_name VARCHAR,
    p_location VARCHAR,
    p_description VARCHAR
)
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO departments (
    department_name,
    location,
    description
)
VALUES (
           p_department_name,
           p_location,
           p_description
       );

RETURN QUERY
SELECT *
FROM departments
WHERE department_id = currval(
        pg_get_serial_sequence('departments', 'department_id')
                      );
END;
$$;


-- 2. GET ALL DEPARTMENTS
CREATE OR REPLACE FUNCTION get_all_departments()
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM departments
ORDER BY department_id;
END;
$$;


-- 3. GET DEPARTMENT BY ID
CREATE OR REPLACE FUNCTION get_department_by_id(
    p_department_id INTEGER
)
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM departments
WHERE department_id = p_department_id;
END;
$$;


-- 4. UPDATE DEPARTMENT
CREATE OR REPLACE FUNCTION update_department(
    p_department_id INTEGER,
    p_department_name VARCHAR,
    p_location VARCHAR,
    p_description VARCHAR
)
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE departments
SET
    department_name = p_department_name,
    location = p_location,
    description = p_description
WHERE department_id = p_department_id;

RETURN QUERY
SELECT *
FROM departments
WHERE department_id = p_department_id;
END;
$$;


-- 5. DELETE DEPARTMENT
CREATE OR REPLACE FUNCTION delete_department(
    p_department_id INTEGER
)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM departments
WHERE department_id = p_department_id;

RETURN FOUND;
END;
$$;


-- 6. SEARCH DEPARTMENTS BY NAME
CREATE OR REPLACE FUNCTION search_departments_by_name(
    p_department_name VARCHAR
)
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM departments
WHERE department_name ILIKE '%' || p_department_name || '%'
ORDER BY department_id;
END;
$$;


-- 7. GET DEPARTMENT BY EXACT NAME
CREATE OR REPLACE FUNCTION get_department_by_name(
    p_department_name VARCHAR
)
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM departments
WHERE LOWER(department_name) = LOWER(p_department_name);
END;
$$;


-- 8. SEARCH DEPARTMENTS BY LOCATION
CREATE OR REPLACE FUNCTION search_departments_by_location(
    p_location VARCHAR
)
RETURNS SETOF departments
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM departments
WHERE location ILIKE '%' || p_location || '%'
ORDER BY department_id;
END;
$$;