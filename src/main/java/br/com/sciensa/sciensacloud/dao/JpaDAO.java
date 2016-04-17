package br.com.sciensa.sciensacloud.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDAO<ENT,ID>  {
	@PersistenceContext(unitName="sciensacloud_DataSource", name="sciensacloud_DataSource")
	private EntityManager manager;

	public ENT insert(ENT entity) throws Exception{
		validarEntidadeNula(entity);
		manager.persist(entity);
		manager.flush();
		return entity;
	}

	public void delete(ENT entity) throws Exception{
		validarEntidadeNula(entity);
		manager.remove(entity);
	}

	public ENT update(ENT entity) throws Exception{
		validarEntidadeNula(entity);
		ENT entidadePersistente = null;
		entidadePersistente = manager.merge(entity);
		return entidadePersistente;
	}	

	private void validarEntidadeNula(ENT ent){
		if(ent == null){
			throw new NullPointerException("A entidade a ser persistida está nula.");
		}
	}

}
