package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import com.fisiosports.modelo.entidades.AgendaConsulta;

public interface IAgenda {
	
	public void agregarConsulta(AgendaConsulta agendaConsulta);
	public List<AgendaConsulta> obtenerConsultas(Date start, Date end);
	public void modificarConsulta(AgendaConsulta agendaConsulta);	
	public void borrarConsulta(AgendaConsulta agendaConsulta);

}
