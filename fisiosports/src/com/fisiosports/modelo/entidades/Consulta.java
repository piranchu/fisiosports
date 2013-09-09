package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Consulta
 *
 */
@Entity

public class Consulta implements Serializable {

	   
	@Id
	private Long id;
	@OneToOne
	private TerapiaFisica terapiaFisica;
	@OneToOne
	private Gimnasio gimansio;
	@OneToOne
	private Quinesiologia quinesiologia;
	
	
	
	private static final long serialVersionUID = 1L;

	public Consulta() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public TerapiaFisica getTerapiaFisica() {
		return terapiaFisica;
	}
	public void setTerapiaFisica(TerapiaFisica terapiaFisica) {
		this.terapiaFisica = terapiaFisica;
	}
	public Gimnasio getGimansio() {
		return gimansio;
	}
	public void setGimansio(Gimnasio gimansio) {
		this.gimansio = gimansio;
	}
	public Quinesiologia getQuinesiologia() {
		return quinesiologia;
	}
	public void setQuinesiologia(Quinesiologia quinesiologia) {
		this.quinesiologia = quinesiologia;
	}
   
}
