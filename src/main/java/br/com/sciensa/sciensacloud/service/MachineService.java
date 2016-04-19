package br.com.sciensa.sciensacloud.service;

import java.util.List;

import br.com.sciensa.sciensacloud.model.Machine;

public interface MachineService {
	public List<Machine> getMachinesByClient(String clientHash) throws Exception;
	public Machine update(Machine machine, String hash) throws Exception;
	public Machine insert(Machine machine,String hash) throws Exception;
	public Boolean ifExistsMachineByClientHash(String clientHash, Long machineId) throws Exception;
	public void delete(String id, String hash) throws Exception;
}
