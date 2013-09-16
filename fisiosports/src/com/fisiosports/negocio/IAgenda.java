package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import com.fisiosports.modelo.entidades.Consulta;

public interface IAgenda {
	
	public void agregarConsulta(Consulta consulta);
	public List<Consulta> obtenerConsultas(Date start, Date end);
	public void modificarConsulta(Consulta consulta);	

}
