package io.github.rhacs.libreria.api;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
class AutoresRestControllerTest {

    private static final String API_AUTORES = "/api/autores";
    private static final String API_AUTORES_ID = API_AUTORES + "/{id}";

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // mostrarTodos()
    // -----------------------------------------------------------------------------------------

    @Test
    void mostrarTodosDeberiaDevolverUnaLista() throws Exception {
        mvc
                // Realizar petición GET a la API
                .perform(get(API_AUTORES))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json devuelto sea un listado de objetos
                .andExpect(jsonPath("$").isArray())
                // Imprimir por consola
                .andDo(print());
    }

    // mostrarUno()
    // -----------------------------------------------------------------------------------------

    @Test
    void mostrarUnoDeberiaDevolverUnObjeto() throws Exception {
        // Identificador numérico del Autor
        Long id = 2L;

        mvc
                // Realizar petición GET a la API
                .perform(get(API_AUTORES_ID, id))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json devuelto tenga un atributo "nombreCompleto" con el valor
                // "Stieg Larsson"
                .andExpect(jsonPath("$.nombreCompleto").value("Stieg Larsson"))
                // Imprimir por consola
                .andDo(print());
    }

    @Test
    void mostrarUnoDeberiaLanzarElementoNoExiste() throws Exception {
        // Identificador numérico del Autor
        Long id = 10000L;

        mvc
                // Realizar petición GET a la API
                .perform(get(API_AUTORES_ID, id))
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
        // Crear nuevo objeto
        Map<String, Object> payload = new HashMap<>();

        // Agregar valores
        payload.put("nombre", "Nombre");
        payload.put("apellido", "Apellido");
        payload.put("nacionalidad", "Nacionalidad");

        // Convertir a json
        String json = new ObjectMapper().writeValueAsString(payload);

        mvc
                // Realizar petición POST a la API
                .perform(post(API_AUTORES)
                        // Establecer tipo de contenido
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer contenido
                        .content(json))
                // Esperar que el estado de la respuesta sea 201 (CREATED)
                .andExpect(status().isCreated())
                // Esperar que el tipo de contenido de la respuesta sea "application/json"
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar que el json devuelto tenga un atributo "id"
                .andExpect(jsonPath("$.id").exists())
                // Esperar que el json devuelto tenga un atributo "nombreCompleto" con el valor
                // "Nombre Apellido"
                .andExpect(jsonPath("$.nombreCompleto")
                        .value(String.format("%s %s", payload.get("nombre"), payload.get("apellido"))))
                // Imprimir por consola
                .andDo(print());
    }

}
