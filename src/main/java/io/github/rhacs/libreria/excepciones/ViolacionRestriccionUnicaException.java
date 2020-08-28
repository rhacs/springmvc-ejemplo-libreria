package io.github.rhacs.libreria.excepciones;

import io.github.rhacs.libreria.modelos.ErrorResponse;

public class ViolacionRestriccionUnicaException extends RuntimeException {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = -8446658053674233341L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ErrorResponse} que contiene la información del error
     */
    private final transient ErrorResponse response;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia de la excepción
     * {@link ViolacionRestriccionUnicaException}
     * 
     * @param response objeto {@link ErrorResponse} que contiene la información del
     *                 error
     */
    public ViolacionRestriccionUnicaException(ErrorResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la {@link ErrorResponse}
     */
    public ErrorResponse getResponse() {
        return response;
    }

}
