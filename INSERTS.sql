INSERT INTO asignatura (nombre, creditos, semestre, objetivos, status, created_at, updated_at) VALUES 
('Matemáticas Avanzadas', 4, 3, 'Desarrollar habilidades de análisis matemático', 'ACTIVO', NOW(), NOW()),
('Programación Web', 3, 2, 'Aprender desarrollo de aplicaciones web', 'ACTIVO', NOW(), NOW()),
('Bases de Datos', 4, 4, 'Diseño y gestión de sistemas de bases de datos', 'ACTIVO', NOW(), NOW()),
('Redes de Computadoras', 3, 5, 'Comprensión de arquitecturas de red', 'ACTIVO', NOW(), NOW()),
('Inteligencia Artificial', 5, 6, 'Fundamentos y aplicaciones de IA', 'ACTIVO', NOW(), NOW()),
('Seguridad Informática', 3, 5, 'Principios de seguridad y ciberseguridad', 'INACTIVO', NOW(), NOW());

INSERT INTO competencias_programa (descripcion, nivel, estado) VALUES
('Competencia en Investigación Científica', 'AVANZADO', 1),
('Competencia en Desarrollo de Software', 'INTERMEDIO', 1),
('Competencia en Gestión de Proyectos', 'BASICO', 1),
('Competencia en Análisis de Datos', 'AVANZADO', 1),
('Competencia en Innovación Tecnológica', 'INTERMEDIO', 1),
('Competencia en Emprendimiento', 'BASICO', 0);

INSERT INTO competencia_asignatura (competencia_programa, descripcion, nivel_competencia, status, created_at, updated_at) VALUES
(1, 'Competencia en análisis de datos', 'INTERMEDIO', 'ACTIVO', NOW(), NOW()),
(2, 'Competencia en desarrollo de software', 'AVANZADO', 'ACTIVO', NOW(), NOW()),
(3, 'Competencia en diseño de bases de datos', 'BASICO', 'ACTIVO', NOW(), NOW()),
(4, 'Competencia en arquitectura de redes', 'INTERMEDIO', 'ACTIVO', NOW(), NOW()),
(5, 'Competencia en inteligencia artificial', 'AVANZADO', 'ACTIVO', NOW(), NOW()),
(6, 'Competencia en seguridad informática', 'INTERMEDIO', 'INACTIVO', NOW(), NOW());

INSERT INTO ra_programa (id_competencia_programa, descripcion, estado) VALUES
(1, 'Resultado de programa en investigación científica avanzada', 1),
(2, 'Resultado de programa en desarrollo de software empresarial', 1),
(3, 'Resultado de programa en gestión de proyectos tecnológicos', 1),
(4, 'Resultado de programa en análisis de big data', 1),
(5, 'Resultado de programa en innovación y desarrollo', 1),
(6, 'Resultado de programa en emprendimiento tecnológico', 0);

INSERT INTO ra_asignatura (competencia_asignatura, descripcion, estado) VALUES
(1, 'Resultado de aprendizaje de análisis estadístico', 1),
(2, 'Resultado de aprendizaje de desarrollo móvil', 1),
(3, 'Resultado de aprendizaje de modelado de datos', 1),
(4, 'Resultado de aprendizaje de configuración de redes', 1),
(5, 'Resultado de aprendizaje de machine learning', 1),
(6, 'Resultado de aprendizaje de ciberseguridad', 0);

INSERT INTO teachers (identification, identification_type, names, last_names, title, teacher_type, status, created_at, updated_at) VALUES
('1234567890', 'CEDULA_CIUDADANIA', 'Juan', 'Pérez González', 'Magíster en Ciencias', 'TIEMPO_COMPLETO', 'ACTIVO', NOW(), NOW()),
('AB987654', 'TARJETA_EXTRANJERA', 'Maria', 'Rodriguez Silva', 'Doctor en Ingeniería', 'CATEDRA', 'ACTIVO', NOW(), NOW()),
('0987654321', 'CEDULA_CIUDADANIA', 'Carlos', 'Martínez Díaz', 'Especialista en Redes', 'PLANTA', 'ACTIVO', NOW(), NOW()),
('XY123456', 'TARJETA_EXTRANJERA', 'Ana', 'García López', 'Magíster en Computación', 'TIEMPO_COMPLETO', 'ACTIVO', NOW(), NOW()),
('5555555555', 'CEDULA_CIUDADANIA', 'Pedro', 'Sánchez Ruiz', 'Doctor en Inteligencia Artificial', 'CATEDRA', 'ACTIVO', NOW(), NOW()),
('CD789012', 'TARJETA_DE_IDENTIDAD', 'Laura', 'Fernández Gómez', 'Ingeniera de Sistemas', 'PLANTA', 'INACTIVO', NOW(), NOW());

INSERT INTO usuarios (username, email, `nombre(s)`, `apellido(s)`, identificacion, `tipo identificacion`, contrasenia, telefono, titulo, rol, estado, created_at, updated_at, account_non_locked, failed_attempts) VALUES
('juan.perez', 		'juan.perez@example.com', 		'Juan', 	'Pérez', '1234567890', 'CEDULA_CIUDADANIA', 'password123', '3101234567', 'Ingeniero', 'DOCENTE', 'ACTIVO', NOW(), NOW(), 1, 0),
('maria.rodriguez', 'maria.rodriguez@example.com', 'Maria', 	'Rodriguez', 'AB987654', 'TARJETA_EXTRANJERA', 'securepass456', '3209876543', 'Magíster', 'ADMIN', 'ACTIVO', NOW(), NOW(), 1, 0),
('carlos.martinez', 'carlos.martinez@example.com', 'Carlos', 	'Martínez', '0987654321', 'CEDULA_CIUDADANIA', 'netpass789', '3156789012', 'Especialista', 'EVALUADOR', 'ACTIVO', NOW(), NOW(), 1, 0),
('ana.garcia', 		'ana.garcia@example.com', 'Ana', 'García', 'XY123456', 'TARJETA_EXTRANJERA', 'cloudpass101', '3187654321', 'Doctora', 'DOCENTE', 'ACTIVO', NOW(), NOW(), 1, 0),
('pedro.sanchez', 	'pedro.sanchez@example.com', 	'Pedro', 	'Sánchez', '5555555555', 'CEDULA_CIUDADANIA', 'airesearch202', '3134567890', 'Doctor', 'EVALUADOR', 'ACTIVO', NOW(), NOW(), 1, 0),
('laura.fernandez', 'laura.fernandez@example.com', 'Laura', 	'Fernández', 'CD789012', 'TARJETA_DE_IDENTIDAD', 'systemspass303', '3112345678', 'Ingeniera', 'ADMIN', 'INACTIVO', NOW(), NOW(), 0, 3);


INSERT INTO RUBRICA (nombre, ra_asignatura_id, create_At, update_At, estado)
VALUES ('Rúbrica de Programación', 1, NOW(), NOW(), "ACTIVO"),
('Rúbrica de Algoritmos', 2, NOW(), NOW(), "ACTIVO");

INSERT INTO criterio_evaluacion (descripcion, ponderacion, id_rubrica, estado)
VALUES ('Calidad del código', 40, 1, "ACTIVO"),
('Eficiencia del algoritmo', 30, 1, "ACTIVO"),
( 'Documentación', 30, 2, "ACTIVO");

INSERT INTO nivel_desempenio (criterio_evaluacion_id, nombre, descripcion, rango_Nota)
VALUES (1, 'EXCELENTE', 'Código limpio, modular y sin errores', '4.5-5.0'),
(2, 'BUENO', 'Algoritmo eficiente, pero con posibles mejoras', '3.8-4.49'),
(3, 'REGULAR', 'Documentación suficiente, pero no exhaustiva', '2.5-3.79');




