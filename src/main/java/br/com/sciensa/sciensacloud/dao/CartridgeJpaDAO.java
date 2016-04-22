package br.com.sciensa.sciensacloud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sciensa.sciensacloud.model.Cartridge;
import br.com.sciensa.sciensacloud.model.Client;

/**
 * Class responsible for database manipulation.
 * @author Felipe Nova
 *
 */
public class CartridgeJpaDAO implements CartridgeDAO  {
	@PersistenceContext(unitName="sciensacloud_DataSource", name="sciensacloud_DataSource")
	private EntityManager manager;

	@Override
	public Cartridge getById(Long id) throws Exception {
		TypedQuery<Cartridge> q = manager.createNamedQuery("search.by.id", Cartridge.class);
		q.setParameter("id", id);
		List<Cartridge> carts = new ArrayList<>();
		carts = q.getResultList();
		if(!carts.isEmpty()){
			return carts.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Cartridge> getAll() throws Exception {
		TypedQuery<Cartridge> q = manager.createNamedQuery("search.all", Cartridge.class);
		return q.getResultList();
	}

	
	

}
