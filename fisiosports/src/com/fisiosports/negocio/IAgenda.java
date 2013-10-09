package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import com.fisiosports.modelo.entidades.ConsultaAgenda;

public interface IAgenda {
	
	public void agregarConsulta(ConsultaAgenda consultaAgenda);
	public List<ConsultaAgenda> obtenerConsultas(Date start, Date end);
	public void modificarConsulta(ConsultaAgenda consultaAgenda);	
	public void borrarConsulta(ConsultaAgenda consultaAgenda);

}
