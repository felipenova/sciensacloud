package br.com.sciensa.sciensacloud.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.sciensa.sciensacloud.model.Machine;

/**
 * Class responsible for database manipulation.
 * @author Felipe Nova
 *
 */
public class MachineJpaDAO extends JpaDAO<Machine> implements MachineDAO  {

	@Override
	public List<Machine> getMachinesByClientHash(String clientHash) throws Exception {
		TypedQuery<Machine> q = getEntityManager().createNamedQuery("search.by.client.hash", Machine.class);
		q.setParameter("clientHash", clientHash.toUpperCase());
		return q.getResultList();
	}
	
	@Override
	public Machine getMachineByClientHashAndMachineId(String clientHash, Long machineId) throws Exception {
		TypedQuery<Machine> q = getEntityManager().createNamedQuery("search.by.client.hash.and.machine.id", Machine.class);
		q.setParameter("clientHash", clientHash.toUpperCase());
		q.setParameter("machineId", machineId);
		List<Machine> machines = q.getResultList();
		if(!machines.isEmpty()){
			return machines.get(0);
		}else{
			return null;
		}
	}
	
	

}
