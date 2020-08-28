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

----------------------------------------------------------------------------------------------------
-- Tabla: lib_libros
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE lib_libros (
    libro_id NUMBER NOT NULL,
    titulo NVARCHAR2(100) NOT NULL,
    titulo_original NVARCHAR2(100) DEFAULT NULL,
    subtitulo NVARCHAR2(100) DEFAULT NULL,
    idioma NVARCHAR2(50) NOT NULL,
    idioma_original NVARCHAR2(50) DEFAULT NULL,
    resumen NVARCHAR2(1000) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    isbn10 NVARCHAR2(10) DEFAULT NULL,
    isbn13 NVARCHAR2(13) DEFAULT NULL,
    portada NVARCHAR2(150) NOT NULL,
    autor_id NUMBER NOT NULL,
    publicador_id NUMBER NOT NULL,

    -- Llave primaria
    CONSTRAINT lib_libros_pk PRIMARY KEY (libro_id),

    -- Llaves foráneas
    CONSTRAINT lib_libros_autor FOREIGN KEY (autor_id) REFERENCES lib_autores (autor_id),
    CONSTRAINT lib_libros_publicador FOREIGN KEY (publicador_id) REFERENCES lib_publicadores (publicador_id),

    -- Columnas únicas
    CONSTRAINT lib_libros_i10 UNIQUE (isbn10),
    CONSTRAINT lib_libros_i13 UNIQUE (isbn13)
);

-- Secuencia
CREATE SEQUENCE lib_libros_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

----------------------------------------------------------------------------------------------------
-- Tabla: lib_libros_categorias
----------------------------------------------------------------------------------------------------

-- Tabla
CREATE TABLE lib_libros_categorias (
    libro_id NUMBER NOT NULL,
    categoria_id NUMBER NOT NULL,

    -- Llaves primarias
    CONSTRAINT lib_libros_categorias_pk PRIMARY KEY (libro_id, categoria_id),

    -- Llaves foráneas
    CONSTRAINT lib_libcat_libros FOREIGN KEY (libro_id) REFERENCES lib_libros (libro_id),
    CONSTRAINT lib_libcat_categorias FOREIGN KEY (categoria_id) REFERENCES lib_categorias (categoria_id)
);
