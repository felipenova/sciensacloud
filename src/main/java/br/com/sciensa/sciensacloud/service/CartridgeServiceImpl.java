package br.com.sciensa.sciensacloud.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sciensa.sciensacloud.dao.CartridgeDAO;
import br.com.sciensa.sciensacloud.model.Cartridge;

@Stateless
public class CartridgeServiceImpl implements CartridgeService{

	@Inject
	private CartridgeDAO cartridgeDAO;

	@Override
	public List<Cartridge> getAll() throws Exception {
		return cartridgeDAO.getAll();
	}

	



}
