package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Paciente
 * 
 */
@NamedQueries({ @NamedQuery(name = "Paciente.all", query = "SELECT p FROM Paciente p ") })
@Entity
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long documento;

	private String nombre;
	private String apellido;
	private String telefono;
	private String correoElectronico;

	@OneToOne (fetch=FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy="paciente")
	//@JoinColumn(name = "EVALUACION_ID", unique = true, nullable = true, updatable = true)
	private Evaluacion evaluacion = new Evaluacion();

	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy="paciente")
	private List<Evaluacion> antecedentes = new LinkedList<Evaluacion>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public List<Evaluacion> getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(List<Evaluacion> antecedentes) {
		this.antecedentes = antecedentes;
	}

}
