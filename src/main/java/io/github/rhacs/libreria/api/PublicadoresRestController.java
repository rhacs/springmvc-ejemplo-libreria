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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.excepciones.ViolacionRestriccionUnicaException;
import io.github.rhacs.libreria.modelos.ErrorResponse;
import io.github.rhacs.libreria.modelos.Publicador;
import io.github.rhacs.libreria.modelos.dto.PublicadorDTO;
import io.github.rhacs.libreria.repositorios.PublicadoresRepositorio;

@RestController
@RequestMapping(path = "/api/publicadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicadoresRestController {

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
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(status.value(), "El elemento no existe");
        response.agregarError("id", response.getMessage(), "Publicador", id);

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
            HttpStatus status = HttpStatus.CONFLICT;
            ErrorResponse response = new ErrorResponse(status.value(), "El nombre está en uso");
            response.agregarError("nombre", "El nombre está en uso", "Publicador", publicador.getNombre());

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

}
