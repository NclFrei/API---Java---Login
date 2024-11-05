package org.example.Resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Model.Cliente;
import org.example.Service.ClienteServico;

import java.sql.SQLException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
// controller
public class ClienteResource {
    ClienteServico cs = new ClienteServico();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST
    @Path("cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionar(Cliente cliente) {
        try {
            cs.adicionar(cliente);
            return Response.status(Response.Status.CREATED).entity("Cliente adicionado com sucesso").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar cliente: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro inesperado: " + e.getMessage()).build();
        }
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Cliente cliente) {
        try {
            Cliente isValid = cs.validarLogin(cliente.getCpf(), cliente.getSenha());
            if (isValid != null) {
                return Response.ok("Login bem-sucedido").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\":\"CPF ou senha incorretos\"}")
                        .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Erro ao acessar o banco de dados: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("{cod_cliente}")
    public Response deleta(@PathParam("cod_cliente") int cod_cliente) {
        try {
            cs.deletar(cod_cliente);
            return Response.status(Response.Status.OK).entity("Deletado com sucesso").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("abc")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletav2(Cliente cliente) {
        try {
            cs.deletar(cliente.getCod_cliente());
            return Response.status(Response.Status.OK).entity("Deletado com sucesso").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTodosElementos() {
        try {
            return Response.ok(cs.buscar()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscarporid")
    public Response buscarPorId(@QueryParam("cod_cliente") int cod_cliente) {
        try {
            return Response.ok(cs.buscarPorID(cod_cliente)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("atualiza")
    public Response atualizaDados(@QueryParam("cod_cliente") int cod_cliente, @QueryParam("nome") String nome) {
        try {
            cs.atualizaDados(cod_cliente, nome);
            return Response.ok("Atualizado com sucesso").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @OPTIONS
    @Path("post")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .build();
    }
}
