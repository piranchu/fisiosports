package com.fisiosports.modelo.entidades;

import com.fisiosports.modelo.entidades.Paciente;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Tratamiento
 *
 */
@Entity

public class Tratamiento implements Serializable {

	   
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String diagnostico;
	@OneToOne
	private Paciente paciente;
	@OneToMany
	private List<Consulta> consultasAgendadas; 
	@OneToMany
	private List<Consulta> consultasRealizadas;
	
	private static final long serialVersionUID = 1L;

	public Tratamiento() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getDiagnostico() {
		return this.diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}   
	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<Consulta> getConsultasAgendadas() {
		return consultasAgendadas;
	}
	public void setConsultasAgendadas(List<Consulta> consultasAgendadas) {
		this.consultasAgendadas = consultasAgendadas;
	}
	public List<Consulta> getConsultasRealizadas() {
		return consultasRealizadas;
	}
	public void setConsultasRealizadas(List<Consulta> consultasRealizadas) {
		this.consultasRealizadas = consultasRealizadas;
	}
   
}
