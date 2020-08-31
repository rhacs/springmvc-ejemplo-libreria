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
@Table(name = Constantes.TABLA_AUTORES)
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = Constantes.SECUENCIA_AUTORES, sequenceName = Constantes.SECUENCIA_AUTORES)
public class Autor {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Autor}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_AUTORES, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.AUTORES_ID)
    private Long id;

    /**
     * Nombre del {@link Autor}
     */
    @NotEmpty
    @Size(max = 50)
    private String nombre;

    /**
     * Apellido del {@link Autor}
     */
    @NotEmpty
    @Size(max = 50)
    private String apellido;

    /**
     * Nacionalidad del {@link Autor}
     */
    @NotEmpty
    @Size(max = 100)
    private String nacionalidad;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Autor}
     */
    public Autor() {

    }

    /**
     * Crea una nueva instancia de la entidad {@link Autor}
     * 
     * @param nombre       nombre del {@link Autor}
     * @param apellido     apellido del {@link Autor}
     * @param nacionalidad nacionalidad del {@link Autor}
     */
    public Autor(String nombre, String apellido, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Crea una nueva instancia de la entidad {@link Autor}
     * 
     * @param id           identificador numérico del {@link Autor}
     * @param nombre       nombre del {@link Autor}
     * @param apellido     apellido del {@link Autor}
     * @param nacionalidad nacionalidad del {@link Autor}
     */
    public Autor(Long id, String nombre, String apellido, String nacionalidad) {
        this(nombre, apellido, nacionalidad);
        this.id = id;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * @return el nombre completo del {@link Autor}
     */
    public String getNombreCompleto() {
        return String.format("%s %s", nombre, apellido);
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

    /**
     * @return el apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return la nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
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

    /**
     * @param apellido el apellido a establecer
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @param nacionalidad la nacionalidad a establecer
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Autor other = (Autor) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Autor [id=%s, nombre=%s, apellido=%s, nacionalidad=%s]", id, nombre, apellido,
                nacionalidad);
    }

}
