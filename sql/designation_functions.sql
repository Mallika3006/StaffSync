-- 1. CREATE
CREATE OR REPLACE FUNCTION create_designation(
    p_title VARCHAR,
    p_min_salary NUMERIC,
    p_max_salary NUMERIC
)
RETURNS TABLE (
    designation_id INTEGER,
    designation_title VARCHAR,
    min_salary NUMERIC,
    max_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
    INSERT INTO designation (
        designation_title,
        min_salary,
        max_salary
    )
    VALUES (
        p_title,
        p_min_salary,
        p_max_salary
    )
    RETURNING
        designation.designation_id,
        designation.designation_title,
        designation.min_salary,
        designation.max_salary;
END;
$$;


-- 2. GET ALL
CREATE OR REPLACE FUNCTION get_all_designations()
RETURNS TABLE (
    designation_id INTEGER,
    designation_title VARCHAR,
    min_salary NUMERIC,
    max_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    d.designation_id,
    d.designation_title,
    d.min_salary,
    d.max_salary
FROM designation d
ORDER BY d.designation_id;
END;
$$;


-- 3. GET BY ID
CREATE OR REPLACE FUNCTION get_designation_by_id(
    p_id INTEGER
)
RETURNS TABLE (
    designation_id INTEGER,
    designation_title VARCHAR,
    min_salary NUMERIC,
    max_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    d.designation_id,
    d.designation_title,
    d.min_salary,
    d.max_salary
FROM designation d
WHERE d.designation_id = p_id;
END;
$$;


-- 4. UPDATE
CREATE OR REPLACE FUNCTION update_designation(
    p_id INTEGER,
    p_title VARCHAR,
    p_min_salary NUMERIC,
    p_max_salary NUMERIC
)
RETURNS TABLE (
    designation_id INTEGER,
    designation_title VARCHAR,
    min_salary NUMERIC,
    max_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
UPDATE designation d
SET
    designation_title = p_title,
    min_salary = p_min_salary,
    max_salary = p_max_salary
WHERE d.designation_id = p_id
    RETURNING
        d.designation_id,
        d.designation_title,
        d.min_salary,
        d.max_salary;
END;
$$;


-- 5. DELETE
CREATE OR REPLACE FUNCTION delete_designation(
    p_id INTEGER
)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
BEGIN
DELETE FROM designation
WHERE designation_id = p_id;

RETURN FOUND;
END;
$$;


-- 6. SEARCH BY TITLE
CREATE OR REPLACE FUNCTION search_designations_by_title(
    p_title VARCHAR
)
RETURNS TABLE (
    designation_id INTEGER,
    designation_title VARCHAR,
    min_salary NUMERIC,
    max_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    d.designation_id,
    d.designation_title,
    d.min_salary,
    d.max_salary
FROM designation d
WHERE d.designation_title ILIKE '%' || p_title || '%'
ORDER BY d.designation_id;
END;
$$;


-- 7. GET BY EXACT TITLE
CREATE OR REPLACE FUNCTION get_designation_by_title(
    p_title VARCHAR
)
RETURNS TABLE (
    designation_id INTEGER,
    designation_title VARCHAR,
    min_salary NUMERIC,
    max_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT
    d.designation_id,
    d.designation_title,
    d.min_salary,
    d.max_salary
FROM designation d
WHERE LOWER(d.designation_title) = LOWER(p_title);
END;
$$;