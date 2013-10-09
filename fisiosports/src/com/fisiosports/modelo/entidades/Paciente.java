package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Paciente
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Paciente.all",
                query="SELECT p FROM Paciente p ")
}) 


public class Paciente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id private Long documento;
	private String nombre;
	private String apellido;
	private String telefono;
	private String correoElectronico;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Evaluacion evaluacionInicial;
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Evaluacion> antecedentes = new LinkedList<Evaluacion>();
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConsultaAgenda> consultasAgendadas = new LinkedList<ConsultaAgenda>();
	
	public Paciente() {
		super();
	}   
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
	public Evaluacion getEvaluacionInicial() {
		return evaluacionInicial;
	}
	public void setEvaluacionInicial(Evaluacion evaluacionInicial) {
		this.evaluacionInicial = evaluacionInicial;
	}
	public List<Evaluacion> getAntecedentes() {
		return antecedentes;
	}
	public void setAntecedentes(List<Evaluacion> antecedentes) {
		this.antecedentes = antecedentes;
	}
	public List<ConsultaAgenda> getConsultasAgendadas() {
		return consultasAgendadas;
	}
	public void setConsultasAgendadas(List<ConsultaAgenda> consultasAgendadas) {
		this.consultasAgendadas = consultasAgendadas;
	}
	
	
}
