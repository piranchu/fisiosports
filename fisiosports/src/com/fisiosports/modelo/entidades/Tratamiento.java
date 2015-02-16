package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//@NamedQueries({
//	@NamedQuery(name = "Tratamiento.findByPaciente", 
//			query = "SELECT t FROM Tratamiento t WHERE t.paciente.id = :idPaciente")
//})

@Entity
public class Tratamiento implements Serializable {
	   
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@OneToMany (mappedBy="tratamiento", cascade = {CascadeType.ALL}) //, fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
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
