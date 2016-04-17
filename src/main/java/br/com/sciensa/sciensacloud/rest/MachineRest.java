package br.com.sciensa.sciensacloud.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Response getMachines(@QueryParam("clientHash") String clientHash)
	{
		List<Machine> machines;
		try {
			machines = machineService.getMachinesByClient(clientHash);
			GenericEntity<List<Machine>> entity = new GenericEntity<List<Machine>>(Lists.newArrayList(machines)) {};
			return Response.status(200).entity(entity).build();
		} catch (Exception e) {
			return Response.status(500).entity(e).build();
		}

	}
}