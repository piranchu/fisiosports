package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.negocio.TipoConsulta;

/**
 * Entity implementation class for Entity: Gimnasio
 * @param <TipoGimnasio>
 *
 */
@Entity
public class Gimnasio extends SesionRehabilitacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection
	private List<TipoGimnasio> tipos = new LinkedList<TipoGimnasio>();

	public Gimnasio() {
		super();
		this.setDescripcion(TipoConsulta.GIMNASIO.getDescripcion());
	}   
	public List<TipoGimnasio> getTipos() {
		return tipos;
	}
	public void setTipos(List<TipoGimnasio> tipos) {
		this.tipos = tipos;
	}
   
}
