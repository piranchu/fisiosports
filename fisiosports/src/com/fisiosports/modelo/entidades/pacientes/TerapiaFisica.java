package com.fisiosports.modelo.entidades.pacientes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fisiosports.modelo.tipos.TipoConsulta;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

/**
 * Entity implementation class for Entity: TerapiaFisica
 *
 */
//@NamedQueries({
//	
//	@NamedQuery(name = "TerapiaFisica.all", query = "SELECT p FROM Paciente p "),
//	@NamedQuery(name = "TerapiaFisica.findTipos", query = "SELECT t.element FROM terapiafisica_tipos t where t.terapiafisica_id = :id ")
//
//})


@Entity
public class TerapiaFisica extends SesionRehabilitacion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@ElementCollection//(fetch=FetchType.EAGER)
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
