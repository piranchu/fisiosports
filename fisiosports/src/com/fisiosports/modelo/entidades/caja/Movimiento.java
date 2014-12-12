package com.fisiosports.modelo.entidades.caja;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fisiosports.modelo.entidades.Paciente;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO")
public class Movimiento {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Double importe;
	private Moneda moneda;
	private CuentaFinanciera cuentaFinanciera;
	private Categoria categoria;
	private Paciente paciente;
	private String observaciones;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public CuentaFinanciera getCuentaFinanciera() {
		return cuentaFinanciera;
	}
	public void setCuentaFinanciera(CuentaFinanciera cuentaFinanciera) {
		this.cuentaFinanciera = cuentaFinanciera;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	} 
	

	
}
