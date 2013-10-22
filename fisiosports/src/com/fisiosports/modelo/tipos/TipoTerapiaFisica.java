package com.fisiosports.modelo.tipos;

import java.util.LinkedList;
import java.util.List;

import com.fisiosports.negocio.TipoConsulta;

public enum TipoTerapiaFisica {
	
	LASER,
	ULTRASONIDO,
	MAGNETO,
	ELECTROANALGESICA,
	ELECTROESTIMULACION,
	FTP;
	
	static public List<TipoTerapiaFisica> getAll(){
		List<TipoTerapiaFisica> all = new LinkedList<TipoTerapiaFisica>();
		for (TipoTerapiaFisica tipo:TipoTerapiaFisica.values()){
			all.add(tipo);
		}
		return all;
	}
	
	public String getNombre(){
		return this.name();
	}

}
