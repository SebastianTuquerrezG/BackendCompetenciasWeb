select * from competencia_asignatura;
select * from competencias_programa;
select * from ra_programa;
select * from ra_asignatura;
select * from usuarios;

describe usuarios;

show tables;

SELECT 
    TABLE_NAME, 
    COLUMN_NAME, 
    COLUMN_TYPE
FROM 
    INFORMATION_SCHEMA.COLUMNS
WHERE 
    TABLE_SCHEMA = 'competencias'
ORDER BY 
    TABLE_NAME, ORDINAL_POSITION;

SELECT 
    CONSTRAINT_NAME, 
    TABLE_NAME, 
    COLUMN_NAME, 
    REFERENCED_TABLE_NAME, 
    REFERENCED_COLUMN_NAME
FROM 
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE 
    TABLE_SCHEMA = 'competencias' 
    AND REFERENCED_TABLE_NAME IS NOT NULL;
