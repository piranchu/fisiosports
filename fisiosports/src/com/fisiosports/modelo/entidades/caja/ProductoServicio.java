package com.fisiosports.modelo.entidades.caja;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ 
	@NamedQuery(
			name = "ProductoServicio.all", 
			query = "SELECT ps FROM ProductoServicio ps "
			), 
	@NamedQuery(
			name = "ProductoServicio.findByConcepto", 
			query = "SELECT ps FROM ProductoServicio ps WHERE ps.concepto.id = :idConcepto "
			) 
})

@Entity
public class ProductoServicio {

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String nombre;
	private Double precio;
	@ManyToOne
	private Concepto concepto;
	
	
	public Concepto getConcepto() {
		return concepto;
	}
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	

	
}
