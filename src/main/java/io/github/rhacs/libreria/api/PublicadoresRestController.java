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
import io.github.rhacs.libreria.excepciones.ViolacionRestriccionUnicaException;
import io.github.rhacs.libreria.modelos.ErrorResponse;
import io.github.rhacs.libreria.modelos.Publicador;
import io.github.rhacs.libreria.modelos.dto.PublicadorDTO;
import io.github.rhacs.libreria.repositorios.PublicadoresRepositorio;

@RestController
@RequestMapping(path = "/api/publicadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicadoresRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre del objeto que se utilizará para los errores
     */
    private static final String NOMBRE_OBJETO = Publicador.class.getSimpleName();

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link PublicadoresRepositorio} que contiene los métodos de consulta y
     * manipulación para el repositorio de {@link Publicador}es
     */
    @Autowired
    private PublicadoresRepositorio publicadoresRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Publicador}es disponibles en el repositorio
     * 
     * @return un objeto {@link List} que contiene la respuesta a la solicitud
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Publicador> mostrarTodos() {
        // Obtener y devolver listado de publicadores
        return publicadoresRepositorio.findAll(Sort.by(Order.asc("id")));
    }

    /**
     * Muestra el detalle del {@link Publicador} solicitado
     * 
     * @param id identificador numérico del {@link Publicador}
     * @return un objeto {@link Publicador} que contiene la respuesta a la solicitud
     */
    @GetMapping(path = "/{id:^[0-9]+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public Publicador mostrarUno(@PathVariable Long id) {
        // Buscar información del Publicador solicitado
        Optional<Publicador> publicador = publicadoresRepositorio.findById(id);

        // Verificar si existe
        if (publicador.isPresent()) {
            // Devolver objeto
            return publicador.get();
        }

        // Preparar respuesta de error
        ErrorResponse response = Metodos.prepararError("id", Constantes.ERROR_NOEXISTE, NOMBRE_OBJETO, id,
                HttpStatus.NOT_FOUND);

        // Lanzar excepción
        throw new ElementoNoExisteException(response);
    }

    // Solicitudes POST
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega un nuevo {@link Publicador} al repositorio
     * 
     * @param publicador objeto {@link Publicador} que contiene la información a
     *                   agregar
     * @return un objeto {@link Publicador} que contiene la respuesta a la solicitud
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Publicador agregarRegistro(@RequestBody @Valid PublicadorDTO publicador) {
        // Buscar información de un publicador existente por el nombre ingresado
        Optional<Publicador> existente = publicadoresRepositorio.findByNombre(publicador.getNombre());

        // Verificar si existe
        if (existente.isPresent()) {
            // Preparar error
            ErrorResponse response = Metodos.prepararError("nombre", Constantes.ERROR_ENUSO, NOMBRE_OBJETO,
                    publicador.getNombre(), HttpStatus.CONFLICT);

            // Lanzar excepción
            throw new ViolacionRestriccionUnicaException(response);
        }

        // Convertir DTO a Entidad
        Publicador pub = new Publicador(publicador.getNombre());

        // Agregar registro al repositorio
        pub = publicadoresRepositorio.save(pub);

        // Devolver objeto
        return pub;
    }

    // Solicitudes PUT
    // -----------------------------------------------------------------------------------------

    /**
     * Edita la información de un registro existente
     * 
     * @param id         identificador numérico del {@link Publicador}
     * @param publicador objeto {@link Publicador} que contiene la información a
     *                   actualizar
     * @return un objeto {@link Publicador}
     */
    @PutMapping(path = "/{id:^[0-9]+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public Publicador editarRegistro(@PathVariable Long id, @RequestBody @Valid PublicadorDTO publicador) {
        // Verificar si los identificadores no coinciden
        if (!id.equals(publicador.getId())) {
            // Preparar error
            ErrorResponse response = Metodos.prepararError("id", Constantes.ERROR_INCONSISTENCIA, NOMBRE_OBJETO,
                    new Object[] { id, publicador.getId() }, HttpStatus.BAD_REQUEST);

            // Lanzar excepción
            throw new InconsistenciaParametrosException(response);
        }

        // Buscar información del registro existente por nombre
        Optional<Publicador> existente = publicadoresRepositorio.findByNombre(publicador.getNombre());

        // Verificar si existe y el identificador sea distinto
        if (existente.isPresent() && !id.equals(existente.get().getId())) {
            // Preparar error
            ErrorResponse response = Metodos.prepararError("nombre", Constantes.ERROR_ENUSO, NOMBRE_OBJETO,
                    publicador.getNombre(), HttpStatus.CONFLICT);

            // Lanzar excepción
            throw new ViolacionRestriccionUnicaException(response);
        }

        // Convertir DTO a Entidad
        Publicador pub = new Publicador(publicador.getId(), publicador.getNombre());

        // Guardar cambios en el repositorio
        pub = publicadoresRepositorio.save(pub);

        // Devolver objeto con los cambios realizados
        return pub;
    }

}
