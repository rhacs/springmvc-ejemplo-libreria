----------------------------------------------------------------------------------------------------
-- Tabla: lib_autores
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE lib_autores (
    autor_id NUMBER NOT NULL,
    nombre NVARCHAR2(50) NOT NULL,
    apellido NVARCHAR2(50) NOT NULL,
    nacionalidad NVARCHAR2(100) NOT NULL,

    -- Llave Primaria
    CONSTRAINT lib_autores_pk PRIMARY KEY (autor_id)
);

-- Secuencia
CREATE SEQUENCE lib_autores_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;
