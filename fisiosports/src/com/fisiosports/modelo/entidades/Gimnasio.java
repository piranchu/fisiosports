package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.fisiosports.modelo.tipos.TipoGimnasio;

/**
 * Entity implementation class for Entity: Gimnasio
 * @param <TipoGimnasio>
 *
 */
@Entity
public class Gimnasio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<TipoGimnasio> tipos = new HashSet<TipoGimnasio>();

	public Gimnasio() {
		super();
	}   
	public Set<TipoGimnasio> getTipos() {
		return tipos;
	}
	public void setTipos(Set<TipoGimnasio> tipos) {
		this.tipos = tipos;
	}
   
}
