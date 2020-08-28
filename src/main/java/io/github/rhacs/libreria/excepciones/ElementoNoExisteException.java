package io.github.rhacs.libreria.excepciones;

import io.github.rhacs.libreria.modelos.ErrorResponse;

public class ElementoNoExisteException extends RuntimeException {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = -6604861059198455433L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ErrorResponse} que contiene la información del error
     */
    private final transient ErrorResponse errorResponse;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia de la excepción {@link ElementoNoExisteException}
     */
    public ElementoNoExisteException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la {@link ErrorResponse}
     */
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

}
