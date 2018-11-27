package br.com.alura.horas.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.horas.model.HoraLancada;
import br.com.alura.horas.model.Usuario;

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
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(horaLancada);
			this.entityManager.getTransaction().commit();
		} catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
		}
	}

	public List<HoraLancada> lista() {
		String jpql = "select h from HoraLancada h";
		TypedQuery<HoraLancada> query = this.entityManager.createQuery(jpql,HoraLancada.class);
		return query.getResultList();
	}
	
	public List<HoraLancada> horasDoUsuario(Usuario usuario){
		String jpql = "select h from HoraLancada h where h.usuario = :usuario order by h.data";
		TypedQuery<HoraLancada> query = this.entityManager.createQuery(jpql,HoraLancada.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}
	
	public HoraLancada getHoraLancadaById(int id) {
		String jpql = "select h from HoraLancada h where h.id = :id";
		TypedQuery<HoraLancada> query = this.entityManager.createQuery(jpql,HoraLancada.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public void removeHoraLancada(int id) {
		try {
			this.entityManager.getTransaction().begin();
			HoraLancada horaLancada = this.entityManager.find(HoraLancada.class, id);
			this.entityManager.remove(horaLancada);
			this.entityManager.getTransaction().commit();
		} catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
		}
	}

}
