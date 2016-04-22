package br.com.sciensa.sciensacloud.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.directory.InvalidAttributesException;
import javax.ws.rs.ForbiddenException;

import br.com.sciensa.sciensacloud.dao.ClientDAO;
import br.com.sciensa.sciensacloud.dao.MachineDAO;
import br.com.sciensa.sciensacloud.model.Client;
import br.com.sciensa.sciensacloud.model.Machine;

@Stateless
public class MachineServiceImpl implements MachineService{

	@Inject
	private MachineDAO machineDAO;

	@Inject
	private ClientDAO clientDAO;

	@Override
	public List<Machine> getMachinesByClient(String clientHash) throws Exception {
		return machineDAO.getMachinesByClientHash(clientHash);
	}

	@Override
	public Machine update(Machine machine,String hash, String id) throws Exception {
		Boolean exists = ifExistsMachineByClientHash(hash, machine.getId());
		if(!exists){
			throw new ForbiddenException("Essa máquina não é desse usuário.");
		}
		
		if(machine.getId() != Long.parseLong(id)){
			throw new IllegalArgumentException("A máquina solicitada para atualização não é igual ao id da URL.");
		}
		return machineDAO.update(machine);
	}

	@Override
	public Machine insert(Machine machine,String hash) throws Exception {
		Client client = clientDAO.getByHash(hash);
		if(client != null){
			machine.setClient(client);
			return machineDAO.insert(machine);
		}else{
			throw new InvalidAttributesException("Cliente não existe");
		}
	}

	@Override
	public void delete(String id, String hash) throws Exception{
		Long macId = null;
		try{
			macId = Long.parseLong(id);
		}catch(NumberFormatException e){
			throw new NumberFormatException("O id deve ser um número inteiro");
		}
		Machine machine = machineDAO.getMachineByClientHashAndMachineId(hash, macId);
		if(machine == null){
			throw new ForbiddenException("Essa máquina não é desse usuário, ou ela não existe.");
		}
		machineDAO.delete(machine);
	}

	public Boolean ifExistsMachineByClientHash(String clientHash, Long machineId) throws Exception{
		if(machineDAO.getMachineByClientHashAndMachineId(clientHash, machineId) != null){
			return true;
		}else{
			return false;
		}
	}


}
