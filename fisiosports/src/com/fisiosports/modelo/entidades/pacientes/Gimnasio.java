package com.fisiosports.modelo.entidades.pacientes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import com.fisiosports.modelo.tipos.TipoConsulta;
import com.fisiosports.modelo.tipos.TipoGimnasio;

/**
 * Entity implementation class for Entity: Gimnasio
 * @param <TipoGimnasio>
 *
 */
@Entity
public class Gimnasio extends SesionRehabilitacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch=FetchType.EAGER)
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
