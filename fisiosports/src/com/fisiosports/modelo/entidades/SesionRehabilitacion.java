package com.fisiosports.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="SESIONREHABILITACION")
public class SesionRehabilitacion extends Consulta implements Serializable{

	private static final long serialVersionUID = 1L;

	private Boolean masaje;
	private Gimnasio gimnasio;
	private TerapiaFisica terapiaFisica;
	
	public Boolean getMasaje() {
		return masaje;
	}
	public void setMasaje(Boolean masaje) {
		this.masaje = masaje;
	}
	public Gimnasio getGimnasio() {
		return gimnasio;
	}
	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}
	public TerapiaFisica getTerapiaFisica() {
		return terapiaFisica;
	}
	public void setTerapiaFisica(TerapiaFisica terapiaFisica) {
		this.terapiaFisica = terapiaFisica;
	}
	
	
	
}
