package br.com.sciensa.sciensacloud.test;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import br.com.sciensa.sciensacloud.model.Cartridge;
import br.com.sciensa.sciensacloud.rest.CartridgeRest;

public class CartridgeTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(CartridgeRest.class);
	}


	@Test
	public void testAllCartridges() throws URISyntaxException {
		Client client=ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:10080/sciensacloud/rest/cartridge");
		Response response=target.request(MediaType.APPLICATION_JSON).get(Response.class);
		List<Cartridge> carts = response.readEntity(new GenericType<List<Cartridge>>() {});
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertTrue("A lista deve ter ao menos 1 registro", carts.size() > 0);
	}

}
