package io.github.rhacs.libreria.modelos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_EMPTY)
public class ErrorDetail {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * El nombre del atributo del objeto afectado
     */
    private String field;

    /**
     * El detalle del error
     */
    private String message;

    /**
     * El nombre del objeto afectado
     */
    private String objectName;

    /**
     * El valor rechazado
     */
    private Object rejectedValue;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía del objeto {@link ErrorDetail}
     */
    public ErrorDetail() {

    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorDetail}
     * 
     * @param field   nombre del atributo del objeto afectado
     * @param message detalle del error
     */
    public ErrorDetail(String field, String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorDetail}
     * 
     * @param field         nombre del atributo del objeto afectado
     * @param message       detalle del error
     * @param objectName    nombre del objeto afectado
     * @param rejectedValue valor rechazado
     */
    public ErrorDetail(String field, String message, String objectName, Object rejectedValue) {
        this(field, message);

        this.objectName = objectName;
        this.rejectedValue = rejectedValue;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el nombre del atributo afectado
     */
    public String getField() {
        return field;
    }

    /**
     * @return el detalle del error
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return el nombre del objeto afectado
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * @return el valor rechazado
     */
    public Object getRejectedValue() {
        return rejectedValue;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param field el nombre del atributo a establecer
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @param message el detalle del error a establecer
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param objectName el nombre del objeto a establecer
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * @param rejectedValue el valor rechazado a establecer
     */
    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("ErrorDetail [field=%s, message=%s, objectName=%s, rejectedValue=%s]", field, message,
                objectName, rejectedValue);
    }

}
