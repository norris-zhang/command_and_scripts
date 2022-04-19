- ### Find all the referencing tables of a table column
```roomsql
SELECT
    r.table_name, r.column_name 
FROM information_schema.constraint_column_usage       u
INNER JOIN information_schema.referential_constraints fk
           ON u.constraint_catalog = fk.unique_constraint_catalog
               AND u.constraint_schema = fk.unique_constraint_schema
               AND u.constraint_name = fk.unique_constraint_name
INNER JOIN information_schema.key_column_usage        r
           ON r.constraint_catalog = fk.constraint_catalog
               AND r.constraint_schema = fk.constraint_schema
               AND r.constraint_name = fk.constraint_name
WHERE
    u.column_name = 'id' AND
    u.table_catalog = 'ams' AND
    u.table_schema = 'public' AND
    u.table_name = 'account';
```