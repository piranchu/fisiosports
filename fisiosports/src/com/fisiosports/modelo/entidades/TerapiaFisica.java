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

import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

/**
 * Entity implementation class for Entity: TerapiaFisica
 *
 */
@Entity
public class TerapiaFisica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<TipoTerapiaFisica> tipos = new HashSet<TipoTerapiaFisica>();

	public TerapiaFisica() {
		super();
	}   

	public Set<TipoTerapiaFisica> getTipos() {
		return tipos;
	}
	public void setTipos(Set<TipoTerapiaFisica> tipos) {
		this.tipos = tipos;
	}
   
}
