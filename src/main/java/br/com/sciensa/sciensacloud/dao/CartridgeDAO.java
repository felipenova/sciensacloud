package br.com.sciensa.sciensacloud.dao;

import java.util.List;

import br.com.sciensa.sciensacloud.model.Cartridge;

public interface CartridgeDAO {
	public Cartridge getById(Long id) throws Exception;
	public List<Cartridge> getAll() throws Exception;

}
