package io.github.rhacs.libreria.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.libreria.modelos.Publicador;
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

}
