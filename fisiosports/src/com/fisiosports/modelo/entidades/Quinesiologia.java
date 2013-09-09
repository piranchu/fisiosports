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
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Quinesiologia extends SesionRehabilitacion implements Serializable {

	   
	@Id
	private Long id;
	private static final long serialVersionUID = 1L;

	public Quinesiologia() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
   
}
