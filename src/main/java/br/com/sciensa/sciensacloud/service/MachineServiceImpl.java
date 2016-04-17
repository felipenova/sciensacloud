package br.com.sciensa.sciensacloud.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sciensa.sciensacloud.dao.MachineDAO;
import br.com.sciensa.sciensacloud.model.Machine;

@Stateless
public class MachineServiceImpl implements MachineService{
	
	@Inject
	private MachineDAO machineDAO;

	@Override
	public List<Machine> getMachinesByClient(String clientHash) throws Exception {
		return machineDAO.getMachinesByClientHash(clientHash);
	}
	

}
