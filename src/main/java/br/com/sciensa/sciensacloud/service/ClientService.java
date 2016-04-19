package br.com.sciensa.sciensacloud.service;

import br.com.sciensa.sciensacloud.model.Client;

public interface ClientService {
	public Client update(Client client, String hash) throws Exception;
	public Client insert(Client client) throws Exception;
	public void delete(String id, String hash) throws Exception;
}
