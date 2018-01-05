package br.com.alura.horas.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.horas.model.HoraLancada;

@RequestScoped
public class HoraLancadaDao {
	
	private EntityManager entityManager;

	@Deprecated
	HoraLancadaDao() {
		this(null);
	}
	
	@Inject
	public HoraLancadaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void adiciona(HoraLancada horaLancada) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(horaLancada);
		this.entityManager.getTransaction().commit();
	}

	public List<HoraLancada> lista() {
		String jpql = "select h from HoraLancada h";
		TypedQuery<HoraLancada> query = this.entityManager.createQuery(jpql,HoraLancada.class);
		return query.getResultList();
	}

}
