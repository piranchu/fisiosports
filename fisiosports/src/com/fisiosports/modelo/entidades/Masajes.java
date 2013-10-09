package com.fisiosports.modelo.entidades;

import com.fisiosports.modelo.entidades.SesionRehabilitacion;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Masajes
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Masajes extends SesionRehabilitacion implements Serializable {

	private static final long serialVersionUID = 1L;

	public Masajes() {
		super();
	}   

   
}
