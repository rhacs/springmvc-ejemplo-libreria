package io.github.rhacs.libreria.api;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.libreria.modelos.Categoria;
import io.github.rhacs.libreria.repositorios.CategoriasRepositorio;

@RestController
@RequestMapping(path = "/api/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaRestController {

    // Atributos
    // -----------------------------------------------------------------------------------------

    @Autowired
    private CategoriasRepositorio categoriasRepositorio;

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

        // Lanzar excepción
        throw new NoSuchElementException(String.format("La Categoría con identificador numérico '%s' no existe", id));
    }

}
