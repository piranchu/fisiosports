package com.fisiosports.modelo.tipos;

import java.util.LinkedList;
import java.util.List;

public enum TipoConsulta {

	TRAUMATOLOGO("Consulta con traumatólogo"),
	DEPORTOLOGO("Consulta con deportólogo"),
	NUTRICIONISTA("Consulta con nutricionista"),
	MASAJES("Masajes"),
	GIMNASIO("Gimasio"),
	TERAPIA_FISICA("Terapia física");
	
	String descripcion;
	
	TipoConsulta(String descripcion){
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
	static public List<TipoConsulta> getAll(){
		List<TipoConsulta> all = new LinkedList<TipoConsulta>();
		for (TipoConsulta tipoConsulta:TipoConsulta.values()){
			all.add(tipoConsulta);
		}
		return all;
	}
	
	
}
