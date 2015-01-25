package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fisiosports.modelo.tipos.TipoTerapiaFisica;
import com.fisiosports.negocio.TipoConsulta;

/**
 * Entity implementation class for Entity: TerapiaFisica
 *
 */
@Entity
public class TerapiaFisica extends SesionRehabilitacion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	private List<TipoTerapiaFisica> tipos = new LinkedList<TipoTerapiaFisica>();

	public TerapiaFisica() {
		super();
		this.setDescripcion(TipoConsulta.TERAPIA_FISICA.getDescripcion());
	}   

	public List<TipoTerapiaFisica> getTipos() {
		return tipos;
	}
	public void setTipos(List<TipoTerapiaFisica> tipos) {
		this.tipos = tipos;
	}
   
}
