package br.com.sciensa.sciensacloud.rest;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.sciensa.sciensacloud.model.Machine;
import br.com.sciensa.sciensacloud.service.MachineService;
import jersey.repackaged.com.google.common.collect.Lists;

@RequestScoped
@Path("/machine")
public class MachineRest {
	@Inject
	private MachineService machineService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getByClientHash")
	public Response getMachines(@QueryParam("clientHash") String clientHash) throws JsonParseException, JsonMappingException, IOException{
		List<Machine> machines;
		try {
			machines = machineService.getMachinesByClient(clientHash);
			GenericEntity<List<Machine>> entity = new GenericEntity<List<Machine>>(Lists.newArrayList(machines)) {};
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9][0-9]*}/{hash}")
	public Response update(@PathParam("hash") String hash,@PathParam("id") String id,Machine ent) {
		try {
			Machine resp;
			resp = machineService.update(ent,hash,id);
			return Response.status(200).entity(resp).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{hash}")
	public Response insert(Machine machine,@PathParam("hash") String hash){
		Response response = null;
		try {	
			Machine m = machineService.insert(machine, hash);
			response = Response.status(201).entity(m).build();
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
			machineService.delete(id, hash);			
			response = Response.status(200).entity("Objeto removido com sucesso").build();
		}catch(Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		return response;
	}
	
	
}