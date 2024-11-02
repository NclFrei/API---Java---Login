package org.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.example.Model.Cliente;
import org.example.Resources.ClienteResource;
import org.example.Service.ClienteServico;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;


    // Mock da dependência ClienteServico
    @Mock
    private ClienteServico clienteServico;

    // Injeção do mock no ClienteResource
    @InjectMocks
    private ClienteResource resource;


    @BeforeEach
    public void setUp() throws Exception {
        //Antes de cada teste ativa o servidor
        // Inicializa os mocks
        MockitoAnnotations.openMocks(this);
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @AfterEach
    public void tearDown() throws Exception {
        //após cada teste para o servidor
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    @Test
    public void testAdicionarComSucesso() throws SQLException {
        Cliente cliente = new Cliente(); // Inicialize o cliente conforme necessário

        // Simulando o comportamento de sucesso do serviço
        doNothing().when(clienteServico).adicionar(cliente);
        //usa doNothing() pois o método é void

        // Testando o método adicionar
        Response response = resource.adicionar(cliente);

        // Verifica se o serviço foi chamado corretamente
        verify(clienteServico, times(1)).adicionar(cliente);

        // Verifica se a resposta foi a esperada
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Cliente adicionado com sucesso", response.getEntity());
    }

    @Test
    public void testDeletarComSucesso() throws SQLException {
        int id = 1;

        // Simulando o comportamento de sucesso do serviço
        doNothing().when(clienteServico).deletar(id);

        // Testando o método deletar
        Response response = resource.deleta(id);

        // Verifica se o serviço foi chamado corretamente
        verify(clienteServico, times(1)).deletar(id);

        // Verifica se a resposta foi a esperada
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Deletado com sucesso", response.getEntity());
    }

    @Test
    public void testDeletarComErro() throws SQLException {
        int id = 1;

        // Simulando uma exceção SQL ao deletar o cliente
        doThrow(new SQLException("Erro ao deletar")).when(clienteServico).deletar(id);
        /*o método deletar(int id) não lançará a exceção SQLException, então o bloco catch(SQLException e)
         não será executado. Como resultado, o código sempre retornará um Response.Status.OK,
          mesmo que você esteja testando um cenário de falha.*/

        // Testando o método deletar
        Response response = resource.deleta(id);

        // Verifica se o serviço foi chamado corretamente
        verify(clienteServico, times(1)).deletar(id);

        // Verifica se a resposta de erro foi a esperada
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        assertEquals("Erro ao deletar", response.getEntity());
    }
}


