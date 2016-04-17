package br.com.sciensa.sciensacloud.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.sciensa.sciensacloud.model.Machine;

@Path("/machine")
public class MachineRest 
{
	@GET
	@Produces("application/json;charset=UTF-8")
	public Response getMachines()
	{
		Machine m = new Machine();
		m.setId(1l);
		m.setCreated(new Date());
		m.setName("Teste");
		m.setUrl("aaa");
		return Response.status(200).entity(m).build();
	}
}