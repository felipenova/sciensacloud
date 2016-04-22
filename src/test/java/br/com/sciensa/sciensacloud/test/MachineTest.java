package br.com.sciensa.sciensacloud.test;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
import br.com.sciensa.sciensacloud.model.Machine;
import br.com.sciensa.sciensacloud.rest.CartridgeRest;

public class MachineTest extends JerseyTest{

	@Override
	protected Application configure() {
		return new ResourceConfig(CartridgeRest.class);
	}


	@Test
	public void testGetByClientHash() throws URISyntaxException {
		Client client=ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:10080/sciensacloud/rest/machine/getByClientHash?clientHash=AAABBB123");
		Response response=target.request(MediaType.APPLICATION_JSON).get(Response.class);
		List<Machine> machines = response.readEntity(new GenericType<List<Machine>>() {});
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertTrue("A lista deve ter ao menos 1 registro", machines.size() > 0);
	}
	
	@Test
	public void testUpdateCartridgesOfMachine() throws URISyntaxException, ParseException {
		Client client=ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:10080/sciensacloud/rest/machine/1/AAABBB123");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		br.com.sciensa.sciensacloud.model.Client cli = new br.com.sciensa.sciensacloud.model.Client();
		cli.setId(1l);
		cli.setHash("AAABBB123");
		cli.setName("Cliente 1");
		cli.setEmail("teste@teste.com");
		cli.setCreated(df.parse("2016-04-17 14:00:01"));
		
		Cartridge c = new Cartridge();
		c.setId(3l);
		c.setName("MongoDB 2.4");
		
		Machine m = new Machine();
		m.setId(1l);
		m.setClient(cli);
		m.setName("Machine 1");
		
		m.setCreated(df.parse("2016-04-17 14:00:01"));
		m.setUrl("machine1.client1.sciensacloud.com.br");
		m.setCartridges(new ArrayList<>());
		m.getCartridges().add(c);
		
		Response response=target.request(MediaType.APPLICATION_JSON).put(Entity.json(m));
		Machine machine = response.readEntity(Machine.class);
		Assert.assertEquals(response.getStatus(), 200);
		Assert.assertTrue("A máquina deveria estar com 1 registro de cartucho",machine.getCartridges().size() == 1);
	}
	
	@Test
	public void testInsertNewMachine() throws URISyntaxException, ParseException {
		Client client=ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:10080/sciensacloud/rest/machine/AAABBB123");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		br.com.sciensa.sciensacloud.model.Client cli = new br.com.sciensa.sciensacloud.model.Client();
		cli.setId(1l);
		cli.setHash("AAABBB123");
		cli.setName("Cliente 1");
		cli.setEmail("teste@teste.com");
		cli.setCreated(df.parse("2016-04-17 14:00:01"));
		
		Cartridge c = new Cartridge();
		c.setId(3l);
		c.setName("MongoDB 2.4");
		
		Cartridge c2 = new Cartridge();
		c2.setId(2l);
		c2.setName("Mysql 5.5");
		
		Machine m = new Machine();
		m.setId(null);
		m.setClient(cli);
		m.setName("Machine 5");
		
		m.setCreated(df.parse("2016-04-22 16:00:01"));
		m.setUrl("machine5.client1.sciensacloud.com.br");
		m.setCartridges(new ArrayList<>());
		m.getCartridges().add(c);
		m.getCartridges().add(c2);
		
		Response response=target.request(MediaType.APPLICATION_JSON).post(Entity.json(m));
		Assert.assertEquals(response.getStatus(), 201);
	}

}
