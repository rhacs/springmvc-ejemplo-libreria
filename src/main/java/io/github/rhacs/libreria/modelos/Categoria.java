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
@Table(name = Constantes.TABLA_CATEGORIAS)
@SequenceGenerator(name = Constantes.SECUENCIA_CATEGORIAS, sequenceName = Constantes.SECUENCIA_CATEGORIAS)
public class Categoria {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico de la {@link Categoria}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_CATEGORIAS, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.CATEGORIAS_ID)
    private Long id;

    /**
     * Nombre de la {@link Categoria}
     */
    @NotEmpty
    @Size(max = 50)
    private String nombre;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Categoria}
     */
    public Categoria() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Categoria}
     * 
     * @param nombre nombre de la {@link Categoria}
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Crea una nueva instancia de la entidad {@link Categoria}
     * 
     * @param id     identificador numérico de la {@link Categoria}
     * @param nombre nombre de la {@link Categoria}
     */
    public Categoria(Long id, String nombre) {
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

        Categoria other = (Categoria) obj;

        return Objects.equals(id, other.id) || Objects.equals(nombre, other.nombre);
    }

    @Override
    public String toString() {
        return String.format("Categoria [id=%s, nombre=%s]", id, nombre);
    }

}
