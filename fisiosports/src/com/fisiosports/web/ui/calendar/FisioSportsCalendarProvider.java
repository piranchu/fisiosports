package com.fisiosports.web.ui.calendar;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fisiosports.modelo.entidades.ConsultaAgenda;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IAgenda;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEventProvider;

public class FisioSportsCalendarProvider  implements  CalendarEventProvider{

	private static final long serialVersionUID = 1L;

	private IAgenda agenda;
	
	public FisioSportsCalendarProvider(){
		this.agenda = FabricaControladores.getIAgenda();
	}
	
	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
		List<CalendarEvent> listaEventos = new LinkedList<CalendarEvent>(); 
		List<ConsultaAgenda> listaConsultas = agenda.obtenerConsultas(startDate, endDate);
		for (ConsultaAgenda consultaAgenda:listaConsultas){
			listaEventos.add(new FisioSportsCalendarEvent(consultaAgenda));
			/*
			System.out.println("[CalendarProvider] evento.caption:"+consulta.getCaption());
			System.out.println("[CalendarProvider] 	evento.start:"+consulta.getStart());
			System.out.println("[CalendarProvider] 	evento.end:"+consulta.getEnd());
			*/
		}

		return listaEventos;
	}

}
