package br.com.sciensa.sciensacloud.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;

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
	
	@Override
	public Machine update(Machine machine,String hash) throws Exception {
		Boolean exists = ifExistsMachineByClientHash(hash, machine.getId());
		if(!exists){
			throw new ForbiddenException("Essa máquina não é desse usuário.");
		}
		return machineDAO.update(machine);
	}
	
	public Boolean ifExistsMachineByClientHash(String clientHash, Long machineId) throws Exception{
		if(machineDAO.getMachineByClientHashAndMachineId(clientHash, machineId) != null){
			return true;
		}else{
			return false;
		}
	}
	

}
