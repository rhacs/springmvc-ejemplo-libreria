package io.github.rhacs.libreria.modelos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.github.rhacs.libreria.Constantes;

@Entity
@Table(name = Constantes.TABLA_LIBROS)
@SequenceGenerator(name = Constantes.SECUENCIA_LIBROS, sequenceName = Constantes.SECUENCIA_LIBROS)
@JsonInclude(content = JsonInclude.Include.NON_EMPTY, value = JsonInclude.Include.NON_EMPTY)
public class Libro {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico del {@link Libro}
     */
    @Id
    @GeneratedValue(generator = Constantes.SECUENCIA_LIBROS, strategy = GenerationType.SEQUENCE)
    @Column(name = Constantes.LIBROS_ID)
    private Long id;

    /**
     * Título del {@link Libro}
     */
    @NotEmpty
    @Size(max = 100)
    private String titulo;

    /**
     * Título original del {@link Libro}
     */
    @Size(max = 100)
    @Column(name = Constantes.LIBROS_TITULO_ORIGINAL)
    private String tituloOriginal;

    /**
     * Subtítulo del {@link Libro}
     */
    @Size(max = 100)
    private String subtitulo;

    /**
     * Idioma del {@link Libro}
     */
    @NotEmpty
    @Size(max = 50)
    private String idioma;

    /**
     * Idioma original del {@link Libro} en caso de que haya sido traducido
     */
    @Size(max = 50)
    @Column(name = Constantes.LIBROS_IDIOMA_ORIGINAL)
    private String idiomaOriginal;

    /**
     * Resumen del {@link Libro}
     */
    @NotEmpty
    @Size(max = 1000)
    private String resumen;

    /**
     * Fecha de publicación del {@link Libro}
     */
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    @Column(name = Constantes.LIBROS_FECHA_PUBLICACION)
    private LocalDate fechaPublicacion;

    /**
     * Código ISBN(10) del {@link Libro}
     */
    @ISBN
    @Size(max = 10)
    private String isbn10;

    /**
     * Código ISBN(13) del {@link Libro}
     */
    @ISBN
    @Size(max = 13)
    private String isbn13;

    /**
     * URL a la imagen de la portada del {@link Libro}
     */
    @NotEmpty
    @URL(protocol = "http")
    @Size(max = 150)
    private String portada;

    /**
     * Litado de {@link Categoria}s a la que pertenece el {@link Libro}
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = Constantes.TABLA_LIBROS_CATEGORIAS, joinColumns = @JoinColumn(referencedColumnName = Constantes.LIBROS_ID), inverseJoinColumns = @JoinColumn(referencedColumnName = Constantes.CATEGORIAS_ID))
    private Set<Categoria> categorias;

    /**
     * {@link Autor} del {@link Libro}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = Constantes.AUTORES_ID, nullable = false)
    private Autor autor;

    /**
     * {@link Publicador} del {@link Libro}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = Constantes.PUBLICADORES_ID, nullable = false)
    private Publicador publicador;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Libro}
     */
    public Libro() {
        categorias = new HashSet<>();
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
     * @return el título
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return el título original
     */
    public String getTituloOriginal() {
        return tituloOriginal;
    }

    /**
     * @return el subtítulo
     */
    public String getSubtitulo() {
        return subtitulo;
    }

    /**
     * @return el idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @return el idioma original
     */
    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    /**
     * @return el resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @return la fecha de publicación
     */
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @return el código isbn10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     * @return el código isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * @return la url a la portada
     */
    public String getPortada() {
        return portada;
    }

    /**
     * @return el listado de {@link Categoria}s
     */
    public Set<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @return el {@link Autor}
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @return el {@link Publicador}
     */
    public Publicador getPublicador() {
        return publicador;
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
     * @param titulo el título a establecer
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param tituloOriginal el título original a establecer
     */
    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    /**
     * @param subtitulo el subtítulo a establecer
     */
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    /**
     * @param idioma el idioma a establecer
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @param idiomaOriginal el idioma original a establecer
     */
    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    /**
     * @param resumen el resumen a establecer
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @param fechaPublicacion la fecha de publicación a establecer
     */
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @param isbn10 el código isbn10 a establecer
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * @param isbn13 el código isbn13 a establecer
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * @param portada la url a la portada a establecer
     */
    public void setPortada(String portada) {
        this.portada = portada;
    }

    /**
     * @param categorias el listado de {@link Categoria}s a establecer
     */
    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @param autor el {@link Autor} a establecer
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @param publicador el {@link Publicador} a establecer
     */
    public void setPublicador(Publicador publicador) {
        this.publicador = publicador;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format(
                "Libro [id=%s, titulo=%s, tituloOriginal=%s, subtitulo=%s, idioma=%s, idiomaOriginal=%s, resumen=%s, fechaPublicacion=%s, isbn10=%s, isbn13=%s, portada=%s, categorias=%s, autor=%s, publicador=%s]",
                id, titulo, tituloOriginal, subtitulo, idioma, idiomaOriginal, resumen, fechaPublicacion, isbn10,
                isbn13, portada, categorias, autor, publicador);
    }

}
