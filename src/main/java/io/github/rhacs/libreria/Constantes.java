package io.github.rhacs.libreria;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.excepciones.InconsistenciaParametrosException;
import io.github.rhacs.libreria.excepciones.ViolacionRestriccionUnicaException;

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

    /**
     * Nombre de la tabla que contiene los registros para el repositorio de
     * {@link Categoria}s
     */
    public static final String TABLA_CATEGORIAS = "lib_categorias";

    /**
     * Nombre de la tabla que contiene los registros para el repositorio de
     * {@link Libro}s
     */
    public static final String TABLA_LIBROS = "lib_libros";

    /**
     * Nombre de la tabla que contiene los registros que relacionan las tablas
     * {@value #TABLA_LIBROS} y {@val #TABLA_CATEGORIAS}
     */
    public static final String TABLA_LIBROS_CATEGORIAS = "lib_libros_categorias";

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

    /**
     * Nombre de la secuencia encargada de generar los valores para la llave
     * primaria de la tabla {@value #TABLA_CATEGORIAS}
     */
    public static final String SECUENCIA_CATEGORIAS = TABLA_CATEGORIAS + "_seq";

    /**
     * Nombre de la secuencia encargada de generar los valores para la llave
     * primaria de la tabla {@value #TABLA_LIBROS}
     */
    public static final String SECUENCIA_LIBROS = TABLA_LIBROS + "_seq";

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

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_CATEGORIAS}
     */
    public static final String CATEGORIAS_ID = "categoria_id";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_LIBROS}
     */
    public static final String LIBROS_ID = "libro_id";

    // Columnas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la columna que almacena el título original del {@link Libro}
     */
    public static final String LIBROS_TITULO_ORIGINAL = "titulo_original";

    /**
     * Nombre de la columna que almacena el idioma original del {@link Libro}
     */
    public static final String LIBROS_IDIOMA_ORIGINAL = "idioma_original";

    /**
     * Nombre de la columna que almacena la fecha de publicación del {@link Libro}
     */
    public static final String LIBROS_FECHA_PUBLICACION = "fecha_publicacion";

    // Mensajes de Error
    // -----------------------------------------------------------------------------------------

    /**
     * Mensaje de error mostrado cuando el valor de un atributo está siendo
     * utilizado por otro registro.
     * 
     * @see ViolacionRestriccionUnicaException
     */
    public static final String ERROR_ENUSO = "El valor está siendo utilizado por otro registro en el repositorio";

    /**
     * Mensaje de error que se muestra cuando el cliente solicita el detalle de un
     * registro que no existe en el repositorio
     * 
     * @see ElementoNoExisteException
     */
    public static final String ERROR_NOEXISTE = "El registro solicitado no existe en el sistema";

    /**
     * Mensaje de error que se muestra cuando hay una inconsistencia de valores de
     * parámetros
     * 
     * @see InconsistenciaParametrosException
     */
    public static final String ERROR_INCONSISTENCIA = "Los valores no coinciden";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
