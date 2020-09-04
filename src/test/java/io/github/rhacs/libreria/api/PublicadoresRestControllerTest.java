package io.github.rhacs.libreria.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

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

import io.github.rhacs.libreria.Constantes;
import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.excepciones.InconsistenciaParametrosException;
import io.github.rhacs.libreria.excepciones.ViolacionRestriccionUnicaException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
class PublicadoresRestControllerTest {

    private static final String API_PUBLICADORES = "/api/publicadores";
    private static final String API_PUBLICADORES_ID = "/api/publicadores/{id}";

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // mostrarTodos()
    // -----------------------------------------------------------------------------------------

    @Test
    void mostrarTodosDeberiaDevolverUnListado() throws Exception {
        mvc
                // Realizar petición GET a la api
                .perform(get(API_PUBLICADORES))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json de la respuesta sea un listado de objetos
                .andExpect(jsonPath("$").isArray())
                // Imprimir por consola
                .andDo(print());
    }

    // mostrarUno()
    // -----------------------------------------------------------------------------------------

    @Test
    void mostrarUnoDeberiaDevolverUnPublicador() throws Exception {
        // Identificador numérico del Publicador
        Long id = 2L;

        mvc
                // Realizar petición GET a la API
                .perform(get(API_PUBLICADORES_ID, id))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json tenga un atributo "id" con el valor "2"
                .andExpect(jsonPath("$.id").value(id))
                // Esperar que el json tenga un atributo "nombre" con el valor "Editorial
                // Planeta"
                .andExpect(jsonPath("$.nombre").value("Editorial Planeta"))
                // Imprimir por consola
                .andDo(print());

    }

    @Test
    void mostrarUnoDeberiaLanzarElementoNoExiste() throws Exception {
        // Identificador numérico del Publicador
        Long id = 100000L;

        mvc
                // Realizar petición GET a la API
                .perform(get(API_PUBLICADORES_ID, id))
                // Esperar que el estado de la respuesta sea 404 (NOT_FOUND)
                .andExpect(status().isNotFound())
                // Esperar que la excepción lanzada sea ElementoNoExisteException
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ElementoNoExisteException))
                // Imprimir por consola
                .andDo(print());
    }

    // agregarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Transactional
    void agregarRegistroDeberiaSerExitoso() throws Exception {
        // Nuevo Publicador
        String json = "{\"nombre\":\"Publicador de prueba\"}";

        mvc
                // Preparar petición
                .perform(
                        // Realizar petición POST a la API
                        post(API_PUBLICADORES)
                                // Establecer tipo de contenido de la solicitud
                                .contentType(MediaType.APPLICATION_JSON)
                                // Establecer contenido de la solicitud
                                .content(json))
                // Esperar que el estado de la respuesta sea 201 (CREATED)
                .andExpect(status().isCreated())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json devuelto tenga un atributo "id"
                .andExpect(jsonPath("$.id").exists())
                // Esperar que el json devuelto tenga un atributo "nombre" con el valor
                // "Publicador de prueba"
                .andExpect(jsonPath("$.nombre").value("Publicador de prueba"))
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void agregarRegistroDeberiaLanzarViolacionRestriccionUnica() throws Exception {
        // Nuevo Publicador
        String json = "{\"nombre\":\"Impedimenta\"}";

        mvc
                // Preparar petición
                .perform(
                        // Realizar petición POST
                        post(API_PUBLICADORES)
                                // Tipo de contenido
                                .contentType(MediaType.APPLICATION_JSON)
                                // Contenido
                                .content(json))
                // Esperar que el estado de la respuesta sea 209 (CONFLICT)
                .andExpect(status().isConflict())
                // Esperar que el tipo de contenido sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json tenga un objeto "error" con un atributo "message" que
                // tiene el valor Constantes.ERROR_ENUSO
                .andExpect(jsonPath("$.error.message").value(Constantes.ERROR_ENUSO))
                // Imprimir por consola
                .andDo(print());
    }

    // editarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Transactional
    void editarRegistroDeberiaSerExitoso() throws Exception {
        // Nueva información
        String json = "{\"id\":1,\"nombre\":\"Editorial LeoPoco\"}";

        mvc
                // Preparar petición
                .perform(
                        // Realizar petición PUT
                        put(API_PUBLICADORES_ID, 1)
                                // Establecer tipo de contenido
                                .contentType(MediaType.APPLICATION_JSON)
                                // Establecer contenido
                                .content(json))
                // Esperar que el estado de la respuesta sea OK (200)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json devuelto tenga un atributo "id" con el valor "1"
                .andExpect(jsonPath("$.id").value(1))
                // Esperar que el json devuelto tenga un atributo "nombre" con el valor
                // "Editorial LeoPoco"
                .andExpect(jsonPath("$.nombre").value("Editorial LeoPoco"))
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void editarRegistroDeberiaLanzarInconsistenciaParametros() throws Exception {
        // Nueva información
        String json = "{\"id\":11,\"nombre\":\"Prueba de inconsistencia\"}";

        mvc
                // Preparar petición
                .perform(
                        // Realizar petición PUT
                        put(API_PUBLICADORES_ID, 2)
                                // Establecer tipo de contenido
                                .contentType(MediaType.APPLICATION_JSON)
                                // Establecer contenido
                                .content(json))
                // Esperar que el estado de la respuesta sea 400 (BAD_REQUEST)
                .andExpect(status().isBadRequest())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json devuelto tenga un objeto "error" con un atributo
                // "message" que tiene el valor Constantes.ERROR_INCONSISTENCIA
                .andExpect(jsonPath("$.error.message").value(Constantes.ERROR_INCONSISTENCIA))
                // Esperar que la excepción lanzada sea InconsistenciaParametrosException
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof InconsistenciaParametrosException))
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void editarRegistroDeberiaLanzarViolacionRestriccionUnica() throws Exception {
        // Nueva información
        String json = "{\"id\":1,\"nombre\":\"Querous\"}";

        mvc
                // Preparar petición
                .perform(
                        // Realizar petición PUT
                        put(API_PUBLICADORES_ID, 1)
                                // Establecer tipo de contenido
                                .contentType(MediaType.APPLICATION_JSON)
                                // Establecer contenido
                                .content(json))
                // Esperar que el estado de la respuesta sea 409 (CONFLICT)
                .andExpect(status().isConflict())
                // Esperar que la excepción lanzada sea ViolacionRestriccionUnicaException
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof ViolacionRestriccionUnicaException))
                // Imprimir por consola
                .andDo(print());
    }

}
