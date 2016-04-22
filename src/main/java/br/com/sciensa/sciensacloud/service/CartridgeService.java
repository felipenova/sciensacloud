package br.com.sciensa.sciensacloud.service;

import java.util.List;

import br.com.sciensa.sciensacloud.model.Cartridge;

public interface CartridgeService {
	public List<Cartridge> getAll() throws Exception;
}
