package com.fisiosports.negocio;

import javax.ejb.Local;

import com.fisiosports.modelo.entidades.seguridad.Usuario;

@Local
public interface ISeguridad {

	public Usuario login(String id, String pass);
	public void modificarUsuario(Usuario usuario);
	
}
