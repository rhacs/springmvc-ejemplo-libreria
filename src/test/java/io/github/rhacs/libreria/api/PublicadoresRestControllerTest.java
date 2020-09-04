package io.github.rhacs.libreria.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;

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

}
