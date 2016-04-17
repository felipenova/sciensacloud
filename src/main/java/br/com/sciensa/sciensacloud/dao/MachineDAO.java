package br.com.sciensa.sciensacloud.dao;

import java.util.List;

import br.com.sciensa.sciensacloud.model.Machine;

public interface MachineDAO {
	public List<Machine> getMachinesByClientHash(String clientHash) throws Exception;
	public Machine insert(Machine machine) throws Exception;
	public void delete(Machine machine) throws Exception;
	public Machine update(Machine machine) throws Exception;
	public Machine getMachineByClientHashAndMachineId(String clientHash, Long machineId) throws Exception;
}
