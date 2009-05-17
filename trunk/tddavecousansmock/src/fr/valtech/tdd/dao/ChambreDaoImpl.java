package fr.valtech.tdd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.valtech.tdd.model.Chambre;

public class ChambreDaoImpl implements ChambreDao {

	private static final String PERSISTENCE_UNIT_NAME = "chambres";
	private EntityManagerFactory factory;

	public ChambreDaoImpl() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Override
	public Chambre findById(int id) {

		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();

		Query q = entityManager
				.createQuery("select c from Chambre c where c.id = :id");
		q.setParameter("id", id);
		Chambre chambre = (Chambre) q.getSingleResult();

		entityManager.getTransaction().rollback();
		entityManager.close();
		return chambre;
	}

	@Override
	public List<Chambre> findByCapacite(int capacite) {
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();

		Query q = entityManager
				.createQuery("select c from Chambre c where c.capacite = :capacite");
		q.setParameter("capacite", capacite);
		List<Chambre> chambres = q.getResultList();

		entityManager.getTransaction().rollback();
		entityManager.close();
		return chambres;
	}
}
