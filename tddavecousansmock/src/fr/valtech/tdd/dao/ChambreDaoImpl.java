package fr.valtech.tdd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import fr.valtech.tdd.model.Chambre;

public class ChambreDaoImpl implements ChambreDao {

	private EntityManagerFactory factory = HotelEntityManagerFactory.getFactory();

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public Chambre findById(int id) {
		EntityManager entityManager = factory.createEntityManager();
		try {
			Query query = entityManager.createQuery("select c from Chambre c where c.id = :id");
			query.setParameter("id", id);
			return (Chambre) query.getSingleResult();
		}
		finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Chambre> findByCapacite(int capacite) {
		EntityManager entityManager = factory.createEntityManager();

		List<Chambre> chambres;
		try {
			Query q = entityManager.createQuery("select c from Chambre c where c.capacite = :capacite");
			q.setParameter("capacite", capacite);
			chambres = q.getResultList();
		}
		finally {
			entityManager.close();
		}
		return chambres;
	}

	@SuppressWarnings("unchecked")
	public List<Chambre> findAll() {
		EntityManager entityManager = factory.createEntityManager();
		try {
			Query query = entityManager.createQuery("select c from Chambre c");
			return query.getResultList();
		}
		finally {
			entityManager.close();
		}
	}
}
