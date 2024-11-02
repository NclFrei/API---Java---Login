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
//controller
public class ClienteResource {
    ClienteServico cs = new ClienteServico();
    @GET //verbo
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @POST //verbo
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
      public Response adicionar (Cliente cliente){
        try {
            cs.adicionar(cliente);
            return Response.status(Response.Status.CREATED).entity("Cliente adicionado com sucesso").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar cliente: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{cod_cliente}")
    public  Response deleta(@PathParam("cod_cliente") int cod_cliente) {
        try {
            cs.deletar(cod_cliente);
            return Response.status(Response.Status.OK).entity("Deletado com sucesso").build();

        } catch (SQLException e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }

    }
        //@DELETE ignora o cabeçalho
        @POST
        @Path("abc")
        @Consumes(MediaType.APPLICATION_JSON)
        public  Response deletav2(Cliente cliente)  {
            try{
                cs.deletar(cliente.getCod_cliente());
                return Response.status(Response.Status.OK).entity("Deletado com sucesso").build();

            }catch (SQLException e){
                return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
            }
    }

    @GET
    @Path("buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTodosElementos(){
       try {
           //List<Cliente> l = cs.buscar();//forma didatica
           return Response.ok(cs.buscar()).build();
       }catch (SQLException e){
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
       }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscarporid")
    public Response buscarPorId(@QueryParam("cod_cliente") int cod_cliente){
       try {
           return Response.ok(cs.buscarPorID(cod_cliente)).build();
       }catch (SQLException e){
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
       }
    }
    @PUT
    @Path("atualiza")
    public Response atualizaDados
    (@QueryParam("cod_cliente") int cod_cliente, @QueryParam("nome")String nome)
    {
       try{
           cs.atualizaDados(cod_cliente,nome);
           return Response.ok("Atualizado com sucesso").build();
       }catch (SQLException e){
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

       }
    }


}
