package com.fisiosports.modelo.entidades;

import com.fisiosports.modelo.entidades.SesionRehabilitacion;
import com.fisiosports.modelo.tipos.TipoGimnasio;

import java.io.Serializable;
import java.lang.Long;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Gimnasio
 * @param <TipoGimnasio>
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Gimnasio extends SesionRehabilitacion implements Serializable {

	   
	@Id
	private Long id;
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	private Set<TipoGimnasio> tipos = new HashSet<TipoGimnasio>();

	public Gimnasio() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Set<TipoGimnasio> getTipos() {
		return tipos;
	}
	public void setTipos(Set<TipoGimnasio> tipos) {
		this.tipos = tipos;
	}
   
}
