package br.com.sciensa.sciensacloud.service;

import java.util.List;

import javax.ejb.Local;

import br.com.sciensa.sciensacloud.model.Machine;

//@Local
public interface MachineService {
	public List<Machine> getMachinesByClient(String clientHash) throws Exception;
}
