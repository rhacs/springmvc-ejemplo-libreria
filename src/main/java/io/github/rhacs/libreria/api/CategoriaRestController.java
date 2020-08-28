package io.github.rhacs.libreria.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.modelos.Categoria;
import io.github.rhacs.libreria.modelos.ErrorResponse;
import io.github.rhacs.libreria.repositorios.CategoriasRepositorio;

@RestController
@RequestMapping(path = "/api/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaRestController {

    // Atributos
    // -----------------------------------------------------------------------------------------

    @Autowired
    private CategoriasRepositorio categoriasRepositorio;

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Prepara una nueva {@link ErrorResponse} para ser entregada al cliente
     * 
     * @param field         nombre del atributo afectado
     * @param message       detalle del error
     * @param objectName    nombre del objeto afectado
     * @param rejectedValue valor rechazado
     * @param status        estado http de la respuesta
     * @return un objeto {@link ErrorResponse}
     */
    private ErrorResponse prepararError(String field, String message, String objectName, Object rejectedValue,
            HttpStatus status) {
        // Crear nueva instancia de ErrorResponse
        ErrorResponse response = new ErrorResponse();

        // Poblar atributos
        response.setStatusCode(status.value());
        response.setMessage(message);

        // Agregar error
        response.agregarError(field, message, objectName, rejectedValue);

        // Devolver objeto
        return response;
    }

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Categoria}s disponibles en el repositorio
     * 
     * @return un objeto {@link List} con la respuesta a la solicitud
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Categoria> mostrarTodas() {
        // Buscar y devolver todas las categorías
        return categoriasRepositorio.findAll();
    }

    /**
     * Muestra los detalles de una {@link Categoria}
     * 
     * @param id identificador numérico de la {@link Categoria}
     * @return un objeto {@link Categoria} con la respuesta a la solicitud
     */
    @GetMapping(path = "/{id:^[0-9]+")
    @ResponseStatus(code = HttpStatus.OK)
    public Categoria mostrarUna(@PathVariable Long id) {
        // Buscar información de la categoría
        Optional<Categoria> categoria = categoriasRepositorio.findById(id);

        // Verificar si existe
        if (categoria.isPresent()) {
            // Devolver objeto
            return categoria.get();
        }

        // Preparar respuesta
        ErrorResponse response = prepararError("id", "El elemento no existe", "Categoria", id, HttpStatus.NOT_FOUND);

        // Lanzar excepción
        throw new ElementoNoExisteException(response);
    }

    // Solicitudes POST
    // -----------------------------------------------------------------------------------------

    /**
     * Agrega una nueva {@link Categoria} al repositorio
     * 
     * @param categoria objeto {@link Categoria} que contiene la información a
     *                  agregar
     * @return un objeto {@link Categoria} con la respuesta a la solicitud
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria agregarRegistro(@Valid Categoria categoria) {
        // Buscar información de la categoría por el nombre ingresado
        Optional<Categoria> existente = categoriasRepositorio.findByNombre(categoria.getNombre());

        // Verificar si existe
        if (existente.isPresent()) {
            // Lanzar excepción
            throw new RuntimeException(String.format(
                    "El nombre '%s' para la Categoría ya está siendo utilizado por un registro en el repositorio",
                    categoria.getNombre()));
        }

        // Guardar categoria
        categoria = categoriasRepositorio.save(categoria);

        // Devolver objeto creado
        return categoria;
    }

}
