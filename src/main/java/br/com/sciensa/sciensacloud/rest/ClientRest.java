package br.com.sciensa.sciensacloud.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sciensa.sciensacloud.model.Client;
import br.com.sciensa.sciensacloud.service.ClientService;

@RequestScoped
@Path("/client")
public class ClientRest {
	@Inject
	private ClientService clientService;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9][0-9]*}/{hash}")
	public Response update(@PathParam("hash") String hash,Client ent) {
		try {
			Client resp;
			resp = clientService.update(ent,hash);
			return Response.status(200).entity(resp).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{hash}")
	public Response insert(Client client){
		Response response = null;
		try {	
			Client c = clientService.insert(client);
			response = Response.status(201).entity(c).build();
		}catch(Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		return response;
	}
	
	
	@DELETE
	@Path("/{id:[0-9][0-9]*}/{hash}")
	public Response delete(@PathParam("id") String id,@PathParam("hash") String hash){
		Response response = null;
		try {
			clientService.delete(id, hash);			
			response = Response.status(200).entity("Objeto removido com sucesso").build();
		}catch(Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		return response;
	}
	
	
}