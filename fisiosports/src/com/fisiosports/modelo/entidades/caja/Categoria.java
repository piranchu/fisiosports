package com.fisiosports.modelo.entidades.caja;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {

	@Id 
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
