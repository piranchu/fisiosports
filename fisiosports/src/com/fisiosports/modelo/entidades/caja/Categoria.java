package com.fisiosports.modelo.entidades.caja;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "Categoria.all", query = "SELECT c FROM Categoria c ")
})

@Entity
public class Categoria  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
