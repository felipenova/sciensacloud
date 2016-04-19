package br.com.sciensa.sciensacloud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.sciensa.sciensacloud.model.Client;

/**
 * Class responsible for database manipulation.
 * @author Felipe Nova
 *
 */
public class ClientJpaDAO extends JpaDAO<Client> implements ClientDAO  {

	@Override
	public Client getByHash(String clientHash) throws Exception {
		TypedQuery<Client> q = getEntityManager().createNamedQuery("search.by.hash", Client.class);
		q.setParameter("hash", clientHash.toUpperCase());
		List<Client> clis = new ArrayList<>();
		clis = q.getResultList();
		if(!clis.isEmpty()){
			return clis.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Boolean verifyIfHashExists(String clientHash) throws Exception {
		TypedQuery<String> q = getEntityManager().createNamedQuery("verify.hash.exists", String.class);
		q.setParameter("hash", clientHash.toUpperCase());
		List<String> hashes = new ArrayList<>();
		hashes = q.getResultList();
		if(!hashes.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	
	

}
