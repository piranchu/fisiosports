package com.fisiosports.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public abstract class SesionRehabilitacion  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
