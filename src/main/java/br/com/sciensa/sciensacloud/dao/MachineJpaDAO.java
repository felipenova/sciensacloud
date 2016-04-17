package br.com.sciensa.sciensacloud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sciensa.sciensacloud.model.Machine;

/**
 * Class responsible for database manipulation.
 * @author Felipe Nova
 *
 */
public class MachineJpaDAO implements MachineDAO {
	@PersistenceContext(unitName="sciensacloud_DataSource", name="sciensacloud_DataSource")
	private EntityManager manager;

	@Override
	public List<Machine> getMachinesByClientHash(String clientHash) throws Exception {
		TypedQuery<Machine> q = manager.createNamedQuery("search.by.client.hash", Machine.class);
		q.setParameter("clientHash", clientHash.toUpperCase());
		return q.getResultList();
	}

}
