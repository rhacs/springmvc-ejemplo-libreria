package io.github.rhacs.libreria.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.excepciones.InconsistenciaParametrosException;
import io.github.rhacs.libreria.excepciones.ViolacionRestriccionUnicaException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
class CategoriaRestControllerTest {

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // mostrarTodas()
    // -----------------------------------------------------------------------------------------

    @Test
    void mostrarTodasDeberiaDevolverUnaLista() throws Exception {
        mvc
                // Realizar petición get a la API para obtener el listado de categorías
                .perform(get("/api/categorias"))
                // Esperar que la cabecera de la respuesta tenga un atributo "Content-Type" con
                // el valor "application/json"
                .andExpect(header().string("Content-Type", "application/json"))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().is2xxSuccessful())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que la respuesta json sea una lista de elementos
                .andExpect(jsonPath("$").isArray())
                // Esperar que el elemento 6 del json tenga un atributo "id" con valor "6"
                .andExpect(jsonPath("$[5].id").value(6))
                // Esperar que el elemento 6 del json tenga un atributo "nombre" con valor
                // "Crimen"
                .andExpect(jsonPath("$[5].nombre").value("Crimen"))
                // Imprimir proceso por consola
                .andDo(print());
    }

    // mostrarUna()
    // -----------------------------------------------------------------------------------------

    @Test
    void mostrarUnaDeberiaDevolverUnaCategoria() throws Exception {
        mvc
                // Realizar petición GET a la API para obtener un registro en particular
                .perform(get("/api/categorias/{id}", 20))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json tenga un atributo "id" con el valor "20"
                .andExpect(jsonPath("$.id").value(20))
                // Esperar que el json tenga un atributo "nombre" con el valor "Ciencia Ficción"
                .andExpect(jsonPath("$.nombre").value("Ciencia Ficción"))
                // Imprimir resultado por consola
                .andDo(print());
    }

    @Test
    void mostrarUnaDeberiaArrojarError() throws Exception {
        mvc
                // Realizar petición GET a la API para obtener un registro en particular
                .perform(get("/api/categorias/{id}", 500))
                // Esperar que el estado de la respuesta sea 404 (NOT_FOUND)
                .andExpect(status().isNotFound())
                // Esperar que la excepción lanzada sea ElementoNoExisteException
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ElementoNoExisteException))
                // Experar que el json contenga un objeto "error" con un atributo "mensaje" que
                // tiene el valor "El elemento no existe"
                .andExpect(jsonPath("$.error.message").value("El elemento no existe"))
                // Imprimir resultado por consola
                .andDo(print());
    }

    // agregarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    // @Transactional Evita que el registro persista en la base de datos
    @Transactional
    void agregarRegistroDeberiaSerExitoso() throws Exception {
        // Nuevo elemento
        String json = "{\"nombre\":\"Prueba desde JUnit\"}";

        mvc
                // Realizar petición POST a la API
                .perform(post("/api/categorias")
                        // Establecer tipo de contenido
                        .contentType(MediaType.APPLICATION_JSON)
                        // Agregar cuerpo a la solicitud
                        .content(json))
                // Esperar que el estado de la respuesta sea 201 (CREATED)
                .andExpect(status().isCreated())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json contenga un atributo "id"
                .andExpect(jsonPath("$.id").exists())
                // Esperar que el json contenga un atributo "nombre" con el valor "Prueba desde
                // JUnit"
                .andExpect(jsonPath("$.nombre").value("Prueba desde JUnit"));
    }

    @Test
    void agregarRegistroDeberiaLanzarExcepcion() throws Exception {
        // Nuevo elemento
        String json = "{\"nombre\":\"Humor\"}";

        mvc
                // Realizar petición POST a la API, con nombre en uso
                .perform(post("/api/categorias")
                        // Establecer el tipo de contenido para la solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer el cuerpo de la solicitud
                        .content(json))
                // Esperar que el estado de la respuesta sea 409 (CONFLICT)
                .andExpect(status().isConflict())
                // Esperar que el json tenga un objeto "error" con el atributo "message" y que
                // su valor sea "El nombre está en uso"
                .andExpect(jsonPath("$.error.message").value("El nombre está en uso"))
                // Esperar que la excepción lanzada sea ViolacionRestriccionUnicaException
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof ViolacionRestriccionUnicaException))
                // Mostrar resultado por consola
                .andDo(print());
    }

    @Test
    void agregarRegistroDeberiaLanzarBadRequest() throws Exception {
        mvc
                // Realizar petición POST a la API con cuerpo vacío
                .perform(post("/api/categorias")
                        // Establecer tipo de contenido
                        .contentType(MediaType.APPLICATION_JSON))
                // Esperar que el estado de la respuesta sea 400 (BAD_REQUEST)
                .andExpect(status().isBadRequest());
    }

    // editarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Transactional
    void editarRegistroDeberiaSerExitoso() throws Exception {
        // Nueva información
        String json = "{\"id\":21,\"nombre\":\"Cuentitos\"}";

        mvc
                // Realizar petición PUT a la API
                .perform(put("/api/categorias/{id}", 21)
                        // Establecer el tipo de contenido de la solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer el contenido de la solicitud
                        .content(json))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json tenga un atributo "nombre" con el valor "Cuentitos"
                .andExpect(jsonPath("$.nombre").value("Cuentitos"))
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void editarRegistroDeberiaLanzarViolacionRestriccionUnica() throws Exception {
        // Nueva información
        String json = "{\"id\":21,\"nombre\":\"Ficción\"}";

        mvc
                // Realizar petición PUT a la API
                .perform(put("/api/categorias/{id}", 21)
                        // Establecer tipo de contenido de la solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer contenido de la solicitud
                        .content(json))
                // Esperar que el estado de la respuesta sea 409 (CONFLICT)
                .andExpect(status().isConflict())
                // Esperar que la excepción lanzada sea ViolacionRestriccionUnicaException
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof ViolacionRestriccionUnicaException))
                // Esperar que el json de respuesta tenga un objeto "error" con un atributo
                // "message" que tiene el valor "El nombre está en uso"
                .andExpect(jsonPath("$.error.message").value("El nombre está en uso"))
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void editarRegistroDeberíaLanzarInconsistenciaParametros() throws Exception {
        // Nueva información
        String json = "{\"id\":21,\"nombre\":\"Cuentazos\"}";

        mvc
                // Realizar petición PUT a la API
                .perform(put("/api/categorias/{id}", 1)
                        // Establecer tipo de contenido
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer contenido
                        .content(json))
                // Esperar que el estado de la respuesta sea 400 (BAD_REQUEST)
                .andExpect(status().isBadRequest())
                // Esperar que la excepción lanzada sea InconsistenciaParametrosException
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof InconsistenciaParametrosException))
                // Esperar que el json de respuesta tenga un objeto "error" con el atributo
                // "message" que contiene el valor "Los identificadores no coinciden"
                .andExpect(jsonPath("$.error.message").value("Los identificadores no coinciden"))
                // Imprimir por consola
                .andDo(print());
    }

    // eliminarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Transactional
    void eliminarRegistroDeberiaDevolverNoContent() throws Exception {
        mvc
                // Realizar petición DELETE a la API para eliminar el registro con id 17
                .perform(delete("/api/categorias/{id}", 17))
                // Esperar que el estado de la respuesta sea 204
                .andExpect(status().isNoContent())
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void eliminarRegistroDeberiaLanzarElementoNoExiste() throws Exception {
        mvc
                // Realizar petición DELETE a la API
                .perform(delete("/api/categorias/{id}", 1000))
                // Esperar que el estado de la respuesta sea 400 (NOT_FOUND)
                .andExpect(status().isNotFound())
                // Esperar que la excepción lanzada sea ElementoNoExisteException
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ElementoNoExisteException))
                // Imprimir por consola
                .andDo(print());
    }

}
