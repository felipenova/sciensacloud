package br.com.sciensa.sciensacloud.service;

import java.util.List;

import br.com.sciensa.sciensacloud.model.Machine;

public interface MachineService {
	public List<Machine> getMachinesByClient(String clientHash) throws Exception;
	public Machine update(Machine machine, String hash) throws Exception;
}
