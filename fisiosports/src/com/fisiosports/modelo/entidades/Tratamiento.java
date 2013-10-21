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
	   
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@OneToMany (fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Consulta> consultas = new LinkedList<Consulta>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
		
}
