package com.fisiosports.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class SesionRehabilitacion  implements Serializable{

	@ManyToOne
	private Paciente paciente;
	
	private static final long serialVersionUID = 1L;

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	
}
