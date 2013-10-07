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
	private Paciente paciente;
	@OneToMany
	private List<Consulta> consultas; 
	
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
	public List<Consulta> getSesiones() {
		return consultas;
	}
	public void setSesiones(List<Consulta> consultas) {
		this.consultas = consultas;
	}
   
}
