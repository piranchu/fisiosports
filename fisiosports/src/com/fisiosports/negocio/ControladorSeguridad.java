package com.fisiosports.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fisiosports.modelo.entidades.seguridad.Usuario;

@Stateless
public class ControladorSeguridad implements ISeguridad{

	@PersistenceContext(unitName="fisiosportsPU")
	private EntityManager em;
	
	@Override
	public Usuario login(String id, String pass) {
	
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario == null){
			return null;
		}
		if (usuario.getPass() == null){
			return null;
		}
		if (usuario.getPass().equals(pass)){
			return usuario;
		}
		return null;
	}
	
	

}
