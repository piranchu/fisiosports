package com.fisiosports.modelo.entidades;

import com.fisiosports.modelo.entidades.Paciente;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.LinkedList;
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
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SesionRehabilitacion> sesiones = new LinkedList<SesionRehabilitacion>();
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConsultaEspecialista> consultasEspecialista = new LinkedList<ConsultaEspecialista>();
	
	
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
	public List<SesionRehabilitacion> getSesiones() {
		return sesiones;
	}
	public void setSesiones(List<SesionRehabilitacion> sesiones) {
		this.sesiones = sesiones;
	}
	public List<ConsultaEspecialista> getConsultasEspecialista() {
		return consultasEspecialista;
	}
	public void setConsultasEspecialista(
			List<ConsultaEspecialista> consultasEspecialista) {
		this.consultasEspecialista = consultasEspecialista;
	}   

	
	
}
