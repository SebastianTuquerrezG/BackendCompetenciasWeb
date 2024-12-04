select * from competencia_asignatura;
select * from competencias_programa;
select * from ra_programa;
select * from ra_asignatura;
select * from usuarios;
select * from criterio_evaluacion;
select * from rubrica;
select * from nivel_desempenio;

describe usuarios;

describe nivel_desempenio;

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

SELECT * 
FROM information_schema.TABLE_CONSTRAINTS 
WHERE TABLE_SCHEMA = 'competencias' 
  AND TABLE_NAME = 'nivel_desempenio';
  

SHOW CREATE TABLE nivel_desempenio;

ALTER TABLE nivel_desempenio DROP CHECK nivel_desempenio_chk_1;
