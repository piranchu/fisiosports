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

//		System.out.println("[ControladorSeguridad.login] login usuario:"+id);
		
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario == null){
			if (id.equalsIgnoreCase("admin")){
				usuario = new Usuario();
				usuario.setId(id);
				usuario.setPass(pass);
				em.persist(usuario);
				return usuario;
			}else{
				return null;
			}
		}
		if (usuario.getPass() == null){
			return null;
		}
		if (usuario.getPass().equals(pass)){
			return usuario;
		}
		
		return null;
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		em.merge(usuario);
		
	}
	
	

}
