package br.com.fiap.visi.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.visi.model.Visi;

public class VisiDao {

	@Inject
	private EntityManager manager;

	public void create(Visi setup) {

		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();

		manager.clear();
	}

	public List<Visi> listAll() {
		TypedQuery<Visi> query = manager.createQuery("SELECT s FROM Setup s", Visi.class);
		return query.getResultList();
	}

	public void delete(Visi setup) {
		manager.getTransaction().begin();
		manager.remove(setup);		
		manager.getTransaction().commit();
	}

	public void update(Visi setup) {
		manager.getTransaction().begin();
		
		System.out.println(setup);
		
		manager.merge(setup);
		
		manager.getTransaction().commit();
		
	}

}
