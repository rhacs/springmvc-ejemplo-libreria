package io.github.rhacs.libreria.modelos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "error")
public class ErrorResponse {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Instante de tiempo que indica cuándo ocurrió el error
     */
    @JsonFormat(pattern = "", shape = JsonFormat.Shape.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timestamp;

    /**
     * Código del Estado HTTP
     */
    private int statusCode;

    /**
     * Detalles del error
     */
    private String message;

    /**
     * Listado de errores
     */
    private Set<ErrorDetail> errors;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link ErrorResponse}
     */
    public ErrorResponse() {
        timestamp = LocalDateTime.now();
        errors = new HashSet<>();
    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorResponse}
     * 
     * @param statusCode código de estado http
     * @param message    detalle del error
     */
    public ErrorResponse(int statusCode, String message) {
        this();
        this.statusCode = statusCode;
        this.message = message;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un nuevo error al listado
     * 
     * @param field   nombre del atributo afectado
     * @param message detalle del error
     */
    public void agregarError(String field, String message) {
        errors.add(new ErrorDetail(field, message));
    }

    /**
     * Agrega un nuevo error al listado
     * 
     * @param field         nombre del atributo afectado
     * @param message       detalle del error
     * @param objectName    nombre del objeto afectado
     * @param rejectedValue valor rechazado
     */
    public void agregarError(String field, String message, String objectName, Object rejectedValue) {
        errors.add(new ErrorDetail(field, message, objectName, rejectedValue));
    }

    /**
     * Extrae la información de un {@link FieldError} y la agrega al listado como un
     * {@link ErrorDetail}
     * 
     * @param fieldError objeto {@link FieldError} que contiene la información del
     *                   error
     */
    public void agregarError(FieldError fieldError) {
        agregarError(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getObjectName(),
                fieldError.getRejectedValue());
    }

    /**
     * Extrae la información de un {@link ObjectError} y la agrega al listado como
     * un {@link ErrorDetail}
     * 
     * @param objectError objeto {@link ObjectError} que contiene la información del
     *                    error
     */
    public void agregarError(ObjectError objectError) {
        agregarError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    /**
     * Extrae la información de una {@link ConstraintViolation} y la agrega al
     * listado como un {@link ErrorDetail}
     * 
     * @param constraintViolation objeto {@link ConstraintViolation} que contiene la
     *                            información del error
     */
    public void agregarError(ConstraintViolation<?> constraintViolation) {
        agregarError(((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName(),
                constraintViolation.getMessage(), constraintViolation.getRootBeanClass().getSimpleName(),
                constraintViolation.getInvalidValue());
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el instante de tiempo en el cual ocurrió el error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return el código de estado http
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return el detalle del error
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return el listado de {@link ErrorDetail}
     */
    public Set<ErrorDetail> getErrors() {
        return errors;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param statusCode el código de estado http a establecer
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param message el detalle del error a establecer
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param errors el listado de {@link ErrorDetail} a establecer
     */
    public void setErrors(Set<ErrorDetail> errors) {
        this.errors = errors;
    }

}
