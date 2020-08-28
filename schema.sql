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

----------------------------------------------------------------------------------------------------
-- Tabla: lib_publicadores
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE lib_publicadores (
    publicador_id NUMBER NOT NULL,
    nombre NVARCHAR2(150) NOT NULL,

    -- Llave primaria
    CONSTRAINT lib_publicadores_pk PRIMARY KEY (publicador_id),

    -- Columnas únicas
    CONSTRAINT lib_publicadores_uq UNIQUE (nombre)
);

-- Secuencia
CREATE SEQUENCE lib_publicadores_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

----------------------------------------------------------------------------------------------------
-- Tabla: lib_categorias
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE lib_categorias (
    categoria_id NUMBER NOT NULL,
    nombre NVARCHAR2(50) NOT NULL,

    -- Llave primaria
    CONSTRAINT lib_categorias_pk PRIMARY KEY (categoria_id),

    -- Columnas únicas
    CONSTRAINT lib_categorias_uq UNIQUE (nombre)
);

-- Secuencia
CREATE SEQUENCE lib_categorias_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;
