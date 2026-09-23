-- ============================================
-- EMPLOYEE SQL FUNCTIONS
-- ============================================


-- 1. GET ALL EMPLOYEES
CREATE OR REPLACE FUNCTION get_all_employees()
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM employees;
END;
$$;


-- 2. GET EMPLOYEE BY ID
CREATE OR REPLACE FUNCTION get_employee_by_id(
    p_employee_id INTEGER
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM employees
WHERE employee_id = p_employee_id;
END;
$$;


-- 3. SEARCH EMPLOYEE BY NAME
CREATE OR REPLACE FUNCTION search_employee_by_name(
    p_name VARCHAR
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM employees
WHERE first_name ILIKE '%' || p_name || '%'
       OR last_name ILIKE '%' || p_name || '%';
END;
$$;


-- 4. GET EMPLOYEE BY EMAIL
CREATE OR REPLACE FUNCTION get_employee_by_email(
    p_email VARCHAR
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM employees
WHERE email = p_email;
END;
$$;


-- 5. GET EMPLOYEES BY DESIGNATION
CREATE OR REPLACE FUNCTION get_employees_by_designation(
    p_designation_id INTEGER
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM employees
WHERE designation_id = p_designation_id;
END;
$$;


-- 6. GET EMPLOYEES BY TEAM
CREATE OR REPLACE FUNCTION get_employees_by_team(
    p_team_id INTEGER
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT *
FROM employees
WHERE team_id = p_team_id;
END;
$$;


-- UPDATE EMPLOYEE PROFILE
CREATE OR REPLACE FUNCTION update_employee_profile(
    p_employee_id INTEGER,
    p_first_name VARCHAR,
    p_last_name VARCHAR,
    p_email VARCHAR,
    p_phone VARCHAR,
    p_date_of_birth DATE,
    p_address VARCHAR,
    p_profile_photo VARCHAR
)
RETURNS SETOF employees
LANGUAGE plpgsql
AS $$
BEGIN

UPDATE employees
SET
    first_name = p_first_name,
    last_name = p_last_name,
    email = p_email,
    phone = p_phone,
    date_of_birth = p_date_of_birth,
    address = p_address,
    profile_photo = p_profile_photo
WHERE employee_id = p_employee_id;

RETURN QUERY
SELECT *
FROM employees
WHERE employee_id = p_employee_id;

END;
$$;