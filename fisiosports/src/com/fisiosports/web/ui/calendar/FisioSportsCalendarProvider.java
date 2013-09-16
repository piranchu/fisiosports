package com.fisiosports.web.ui.calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.fisiosports.modelo.entidades.Consulta;
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
		List<Consulta> listaConsultas = agenda.obtenerConsultas(startDate, endDate);
		for (Consulta consulta:listaConsultas){
			listaEventos.add(new FisioSportsCalendarEvent(consulta));
			System.out.println("[CalendarProvider] evento.caption:"+consulta.getCaption());
			System.out.println("[CalendarProvider] 	evento.start:"+consulta.getStart());
			System.out.println("[CalendarProvider] 	evento.end:"+consulta.getEnd());
		}
		/*
		if (listaEventos.isEmpty()){
			Consulta consulta1 = new Consulta();
			consulta1.setCaption("caption 1");
			consulta1.setDescription("desc 1");
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
			gc.add(GregorianCalendar.HOUR, -8);
			consulta1.setStart(gc.getTime());
			gc.add(GregorianCalendar.HOUR, 1);
			consulta1.setEnd(gc.getTime());
			FisioSportsCalendarEvent evento1 = new FisioSportsCalendarEvent(consulta1);
			System.out.println("[CalendarProvider] 	evento1.start:"+evento1.getStart());
			System.out.println("[CalendarProvider] 	evento1.end:"+evento1.getEnd());
			listaEventos.add(evento1);
			
			Consulta consulta2 = new Consulta();
			consulta2.setCaption("caption DOS");
			consulta2.setDescription("desc DOS");
			gc.add(GregorianCalendar.HOUR, 6);
			consulta2.setStart(gc.getTime());
			gc.add(GregorianCalendar.HOUR, 1);
			gc.add(GregorianCalendar.MINUTE, 30);
			consulta1.setEnd(gc.getTime());
			FisioSportsCalendarEvent evento2 = new FisioSportsCalendarEvent(consulta1);
			System.out.println("[CalendarProvider] 	evento2.start:"+evento2.getStart());
			System.out.println("[CalendarProvider] 	evento2.end:"+evento2.getEnd());
			listaEventos.add(evento2);			
		}
		*/
		return listaEventos;
	}

}
