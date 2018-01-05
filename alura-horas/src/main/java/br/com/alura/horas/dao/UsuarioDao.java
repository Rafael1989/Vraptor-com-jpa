package br.com.alura.horas.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.alura.horas.model.Usuario;


@RequestScoped
public class UsuarioDao {
	
	private final EntityManager entityManager;
	
	@Inject
	public UsuarioDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Deprecated
	UsuarioDao() {
		this(null);
	}
	
	public void adiciona(Usuario usuario) {
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	public List<Usuario> lista(){
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> query = entityManager.createQuery(jpql,Usuario.class);
		return query.getResultList();
	}
	
	public Usuario busca(Usuario usuario) {
		try {
			String jpql = "select u from Usuario u where u.login = :login and u.senha = :senha";
			TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", usuario.getSenha());
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		
	}

}
