package com.fisiosports.modelo.entidades;

import com.fisiosports.modelo.entidades.SesionRehabilitacion;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Quinesiologia
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Quinesiologia extends SesionRehabilitacion implements Serializable {

	private static final long serialVersionUID = 1L;

	public Quinesiologia() {
		super();
	}   

   
}
