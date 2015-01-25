package com.fisiosports.modelo.tipos;

import java.util.LinkedList;
import java.util.List;

public enum TipoGimnasio {
	
	FUERZA,
	PROPIOCEPCION,
	ESTABILIDAD;

	static public List<TipoGimnasio> getAll(){
		List<TipoGimnasio> all = new LinkedList<TipoGimnasio>();
		for (TipoGimnasio tipo:TipoGimnasio.values()){
			all.add(tipo);
		}
		return all;
	}
	
	public String getNombre(){
		return this.name();
	}

	
	
}
