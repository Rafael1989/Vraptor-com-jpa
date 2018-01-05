package br.com.alura.horas.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.alura.horas.model.Usuario;

@SessionScoped
@Named
public class UsuarioLogado implements Serializable{

	private static final long serialVersionUID = 3424716756999542007L;
	
	private Usuario usuario;
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void desloga() {
		this.usuario = null;
	}

}
