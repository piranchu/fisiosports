package com.fisiosports.modelo.entidades.caja;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({ 
	@NamedQuery(
			name = "Concepto.all", 
			query = "SELECT c FROM Concepto c "
			) 
})

@Entity
public class Concepto  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String nombre;
	@OneToMany
	private List<ProductoServicio> productosServicios = new LinkedList<ProductoServicio>();
	
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
	public List<ProductoServicio> getProductosServicios() {
		return productosServicios;
	}
	public void setProductosServicios(List<ProductoServicio> productosServicios) {
		this.productosServicios = productosServicios;
	}
	
	
}
