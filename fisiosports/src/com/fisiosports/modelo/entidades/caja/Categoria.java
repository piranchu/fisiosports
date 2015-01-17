package com.fisiosports.modelo.entidades.caja;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
