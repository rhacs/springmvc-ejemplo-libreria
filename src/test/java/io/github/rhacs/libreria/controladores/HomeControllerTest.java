package io.github.rhacs.libreria.controladores;

import static org.hamcrest.CoreMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
class HomeControllerTest {

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // home()
    // -----------------------------------------------------------------------------------------

    @Test
    void homeDeberiaMostrarVistaHome() throws Exception {
        mvc
                // Realizar petición GET a la página
                .perform(get("/"))
                // Esperar que el estado de la respuesta sea 200 (OK)
                .andExpect(status().isOk())
                // Esperar que la vista devuelta por el controlador se llame "home"
                .andExpect(view().name("home"))
                // Esperar que el modelo tenga un atributo "categorias"
                .andExpect(model().attributeExists("categorias"))
                // Esperar que el atributo "categorias" del modelo sea un objeto iterable (List)
                .andExpect(model().attribute("categorias", isA(Iterable.class)))
                // Imprimir por consola
                .andDo(print());
    }

}
