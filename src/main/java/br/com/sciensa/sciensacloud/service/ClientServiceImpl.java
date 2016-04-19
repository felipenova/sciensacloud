package br.com.sciensa.sciensacloud.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;

import br.com.sciensa.sciensacloud.dao.ClientDAO;
import br.com.sciensa.sciensacloud.model.Client;

@Stateless
public class ClientServiceImpl implements ClientService{

	@Inject
	private ClientDAO clientDAO;

	@Override
	public Client update(Client client,String hash) throws Exception {
		Boolean exists = clientDAO.getByHash(hash) == null ? false:true;
		if(!exists){
			throw new ForbiddenException("Usuário não encontrado para atualização");
		}
		return clientDAO.update(client);
	}

	@Override
	public Client insert(Client client) throws Exception {
		client.setHash(generateHash());
		return clientDAO.insert(client);
	}

	@Override
	public void delete(String id, String hash) throws Exception{
		Client client = clientDAO.getByHash(hash);
		if(client == null){
			throw new ForbiddenException("Cliente inexistente");
		}
		clientDAO.delete(client);
	}

	private String generateHash() throws Exception{
		SecureRandom random = new SecureRandom();
		String hash = new BigInteger(130, random).toString(32);
		Boolean hashExists = clientDAO.verifyIfHashExists(hash);
		if(hashExists){
			return generateHash();
		}else{
			return hash;
		}
	}



}
