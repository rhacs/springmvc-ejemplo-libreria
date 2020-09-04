package io.github.rhacs.libreria.modelos.dto;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) para la entidad {@link Publicador}
 */
public class PublicadorDTO {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Long id;

    @NotEmpty
    @Size(max = 150)
    private String nombre;

    // Constructores
    // -----------------------------------------------------------------------------------------

    public PublicadorDTO() {

    }

    public PublicadorDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("PublicadorDTO [id=%s, nombre=%s]", id, nombre);
    }

}
