package io.github.rhacs.libreria.modelos.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.libreria.modelos.Autor;

/**
 * Data Transfer Object (DTO) para la entidad {@link Autor}
 */
public class AutorDTO {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String nombre;

    @NotEmpty
    @Size(max = 50)
    private String apellido;

    @NotEmpty
    @Size(max = 100)
    private String nacionalidad;

    // Constructores
    // -----------------------------------------------------------------------------------------

    public AutorDTO() {

    }

    public AutorDTO(Long id, String nombre, String apellido, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("AutorDTO [id=%s, nombre=%s, apellido=%s, nacionalidad=%s]", id, nombre, apellido,
                nacionalidad);
    }

}
