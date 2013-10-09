package com.fisiosports.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Evaluacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String diagnostico;
	private String indicaciones;
	private Tratamiento tratamiento;

}
