package br.com.sciensa.sciensacloud.dao;

import java.util.List;

import br.com.sciensa.sciensacloud.model.Machine;

public interface MachineDAO {
	public List<Machine> getMachinesByClientHash(String clientHash) throws Exception;
}
