package br.com.sciensa.sciensacloud.dao;

import br.com.sciensa.sciensacloud.model.Client;

public interface ClientDAO {
	public Client getByHash(String clientHash) throws Exception;
	public Boolean verifyIfHashExists(String clientHash) throws Exception;
	public Client insert(Client client) throws Exception;
	public void delete(Client client) throws Exception;
	public Client update(Client client) throws Exception;
}
