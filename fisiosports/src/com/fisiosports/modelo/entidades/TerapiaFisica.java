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
	private Set<TipoTerapiaFisica> tipos = new HashSet<TipoTerapiaFisica>();

	public TerapiaFisica() {
		super();
		this.setDescripcion(TipoConsulta.TERAPIA_FISICA.getDescripcion());
	}   

	public Set<TipoTerapiaFisica> getTipos() {
		return tipos;
	}
	public void setTipos(Set<TipoTerapiaFisica> tipos) {
		this.tipos = tipos;
	}
   
}
