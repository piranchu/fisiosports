package com.fisiosports.modelo.entidades;

import com.fisiosports.modelo.entidades.SesionRehabilitacion;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

import java.io.Serializable;
import java.lang.Long;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TerapiaFisica
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class TerapiaFisica extends SesionRehabilitacion implements Serializable {

	   
	@Id
	private Long id;
	@Enumerated(EnumType.STRING)
	private Set<TipoTerapiaFisica> tipos = new HashSet<TipoTerapiaFisica>();

	
	private static final long serialVersionUID = 1L;

	public TerapiaFisica() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Set<TipoTerapiaFisica> getTipos() {
		return tipos;
	}
	public void setTipos(Set<TipoTerapiaFisica> tipos) {
		this.tipos = tipos;
	}
   
}
