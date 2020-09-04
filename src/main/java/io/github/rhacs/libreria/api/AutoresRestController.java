package io.github.rhacs.libreria.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.libreria.Constantes;
import io.github.rhacs.libreria.Metodos;
import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.excepciones.InconsistenciaParametrosException;
import io.github.rhacs.libreria.modelos.Autor;
import io.github.rhacs.libreria.modelos.ErrorResponse;
import io.github.rhacs.libreria.modelos.dto.AutorDTO;
import io.github.rhacs.libreria.repositorios.AutoresRepositorio;

@RestController
@RequestMapping(path = "/api/autores", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutoresRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre del objeto que se utilizará en los errores
     */
    private static final String NOMBRE_OBJETO = Autor.class.getSimpleName();

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link AutoresRepositorio} que contiene los métodos de consulta y
     * manipulación para el repositorio de {@link Autor}es
     */
    @Autowired
    private AutoresRepositorio autoresRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Autor}es disponibles en el respositorio
     * 
     * @return un objeto {@link List} que contiene la respuesta a la solicitud
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Autor> mostrarTodos() {
        // Buscar y devolver listado de autores
        return autoresRepositorio.findAll(Sort.by(Order.asc("id")));
    }

    /**
     * Muestra el detalle del registro solicitado
     * 
     * @param id identificador numérico del {@link Autor}
     * @return un objeto {@link Autor} que contiene la respuesta a la solicitud
     */
    @GetMapping(path = "/{id:^[0-9]+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public Autor mostrarUno(@PathVariable Long id) {
        // Buscar información del Autor solicitado en el repositorio
        Optional<Autor> autor = autoresRepositorio.findById(id);

        // Verificar si existe
        if (autor.isPresent()) {
            // Devolver objeto
            return autor.get();
        }

        // Preparar error
        ErrorResponse response = Metodos.prepararError("id", Constantes.ERROR_NOEXISTE, NOMBRE_OBJETO, id,
                HttpStatus.NOT_FOUND);

        // Lanzar excepción
        throw new ElementoNoExisteException(response);
    }

    // Solicitudes POST
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un nuevo registro al repositorio
     * 
     * @param autor objeto {@link Autor} que contiene la información a agregar
     * @return un objeto {@link Autor} que contiene la respuesta a la solicitud
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Autor agregarRegistro(@RequestBody @Valid AutorDTO autor) {
        // Convertir DTO a Entidad
        Autor a = new Autor(autor.getNombre(), autor.getApellido(), autor.getNacionalidad());

        // Guardar registro en el repositorio
        a = autoresRepositorio.save(a);

        // Devolver objeto guardado
        return a;
    }

    // Solicitudes PUT
    // -----------------------------------------------------------------------------------------

    /**
     * Edita la información de un registro en el repositorio
     * 
     * @param id    identificador numérico del {@link Autor}
     * @param autor objeto {@link Autor} que contiene la información a editar
     * @return un objeto {@link Autor} que contiene la respuesta a la solicitud
     */
    @PutMapping(path = "/{id:^[0-9]+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public Autor editarRegistro(@PathVariable Long id, @RequestBody @Valid AutorDTO autor) {
        // Verificar si los identificadores no coinciden
        if (!id.equals(autor.getId())) {
            // Preparar error
            ErrorResponse response = Metodos.prepararError("id", Constantes.ERROR_INCONSISTENCIA, NOMBRE_OBJETO,
                    new Object[] { id, autor.getId() }, HttpStatus.BAD_REQUEST);

            // Lanzar excepción
            throw new InconsistenciaParametrosException(response);
        }

        // Convertir DTO a Entidad
        Autor a = new Autor(autor.getId(), autor.getNombre(), autor.getApellido(), autor.getNacionalidad());

        // Guardar cambios en el repositorio
        a = autoresRepositorio.save(a);

        // Devolver objeto modificado
        return a;
    }

}
