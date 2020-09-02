package io.github.rhacs.libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.rhacs.libreria.modelos.Categoria;
import io.github.rhacs.libreria.repositorios.CategoriasRepositorio;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link CategoriasRepositorio} que contiene los métodos de consulta y
     * manipulación para el repositorio de {@link Categoria}s
     */
    @Autowired
    private CategoriasRepositorio categoriasRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra la página principal
     * 
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista a mostrar
     */
    @GetMapping
    public String home(Model modelo) {
        // Buscar todas las categorías, ordenar los resultados por nombre
        List<Categoria> categorias = categoriasRepositorio.findAll(Sort.by(Order.asc("nombre")));

        // Agregar categorías al modelo
        modelo.addAttribute("categorias", categorias);

        // Devolver nombre de la vista
        return "home";
    }

}
