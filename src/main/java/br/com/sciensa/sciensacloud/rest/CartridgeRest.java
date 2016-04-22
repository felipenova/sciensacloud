package br.com.sciensa.sciensacloud.rest;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.sciensa.sciensacloud.model.Cartridge;
import br.com.sciensa.sciensacloud.service.CartridgeService;
import jersey.repackaged.com.google.common.collect.Lists;

@RequestScoped
@Path("/cartridge")
public class CartridgeRest {
	@Inject
	private CartridgeService cartridgeService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCartridges() throws JsonParseException, JsonMappingException, IOException{
		List<Cartridge> cartridges;
		try {
			cartridges = cartridgeService.getAll();
			GenericEntity<List<Cartridge>> entity = new GenericEntity<List<Cartridge>>(Lists.newArrayList(cartridges)) {};
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	
}