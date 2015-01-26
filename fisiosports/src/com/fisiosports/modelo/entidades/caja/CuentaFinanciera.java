package com.fisiosports.modelo.entidades.caja;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "CuentaFinanciera.all", query = "SELECT c FROM CuentaFinanciera c ")
})

@Entity
public class CuentaFinanciera implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String nombre;
	private Double saldo;
	@Enumerated(EnumType.STRING)
	private Moneda moneda;
	private String descripcion;
	
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
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
