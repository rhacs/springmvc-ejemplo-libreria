package io.github.rhacs.libreria;

public class Constantes {

    // Tablas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la tabla que contiene los registros para el repositorio de
     * {@link Autor}es
     */
    public static final String TABLA_AUTORES = "lib_autores";

    /**
     * Nombre de la tabla que contiene los registros para el repositorio de
     * {@link Publicador}es
     */
    public static final String TABLA_PUBLICADORES = "lib_publicadores";

    // Secuencias
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la secuencia encargada de generar los valores para la llave
     * primaria de la tabla {@value #TABLA_AUTORES}
     */
    public static final String SECUENCIA_AUTORES = TABLA_AUTORES + "_seq";

    /**
     * Nombre de la secuencia encargada de generar los valores para la llave
     * primaria de la tabla {@value #TABLA_PUBLICADORES}
     */
    public static final String SECUENCIA_PUBLICADORES = TABLA_PUBLICADORES + "_seq";

    // Llaves Primarias
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_AUTORES}
     */
    public static final String AUTORES_ID = "autor_id";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_PUBLICADORES}
     */
    public static final String PUBLICADORES_ID = "publicador_id";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
