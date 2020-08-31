package io.github.rhacs.libreria.modelos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.github.rhacs.libreria.Constantes;

@Entity
@Table(name = Constantes.TABLA_PUBLICADORES)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = Constantes.SECUENCIA_PUBLICADORES, sequenceName = Constantes.SECUENCIA_PUBLICADORES)
public class Publicador {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Publicador}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_PUBLICADORES, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.PUBLICADORES_ID)
    private Long id;

    /**
     * Nombre del {@link Publicador}
     */
    @NotEmpty
    @Size(max = 150)
    private String nombre;

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva intancia vacía de la entidad {@link Publicador}
     */
    public Publicador() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Publicador}
     * 
     * @param nombre nombre del {@link Publicador}
     */
    public Publicador(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Crea una nueva instancia de la entidad {@link Publicador}
     * 
     * @param id     identificador numérico del {@link Publicador}
     * @param nombre nombre del {@link Publicador}
     */
    public Publicador(Long id, String nombre) {
        this(nombre);
        this.id = id;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public Long getId() {
        return id;
    }

    /**
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param nombre el nombre a establecer
     */
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

        Publicador other = (Publicador) obj;

        return Objects.equals(id, other.id) || Objects.equals(nombre, other.nombre);
    }

    @Override
    public String toString() {
        return String.format("Publicador [id=%s, nombre=%s]", id, nombre);
    }

}
