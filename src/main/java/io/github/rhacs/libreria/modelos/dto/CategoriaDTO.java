package io.github.rhacs.libreria.modelos.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) para la entidad {@link Categoria}
 * 
 * @author Ricardo
 */
public class CategoriaDTO {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Long id;

    @NotEmpty
    @Size(max = 50)
    private String nombre;

    // Constructores
    // -----------------------------------------------------------------------------------------

    public CategoriaDTO() {

    }

    public CategoriaDTO(Long id, String nombre) {
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
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        CategoriaDTO other = (CategoriaDTO) obj;

        return Objects.equals(id, other.id) || Objects.equals(nombre, other.nombre);
    }

    @Override
    public String toString() {
        return String.format("CategoriaDTO [id=%s, nombre=%s]", id, nombre);
    }

}
