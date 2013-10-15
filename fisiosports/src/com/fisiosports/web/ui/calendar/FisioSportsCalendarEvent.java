package com.fisiosports.web.ui.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fisiosports.modelo.entidades.AgendaConsulta;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IAgenda;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeListener;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeNotifier;

public class FisioSportsCalendarEvent implements EditableCalendarEvent, EventChangeNotifier, CalendarEvent {

	private static final long serialVersionUID = 1L;
	
	private IAgenda agenda;
	private AgendaConsulta agendaConsulta;
    private transient List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();
	
	public FisioSportsCalendarEvent(AgendaConsulta agendaConsulta){
		this.agendaConsulta = agendaConsulta;
		this.agenda = FabricaControladores.getIAgenda();
	}

    protected void fireEventChange() {
        EventChangeEvent event = new EventChangeEvent(this);
        for (EventChangeListener listener : listeners) {
            listener.eventChange(event);
        }
        agenda.modificarConsulta(this.getConsulta());
    }

	@Override
	public Date getStart() {
		// TODO Auto-generated method stub
		return this.agendaConsulta.getStart();
	}

	@Override
	public Date getEnd() {
		// TODO Auto-generated method stub
		return this.agendaConsulta.getEnd();
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return this.agendaConsulta.getCaption();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.agendaConsulta.getDescription();
	}

	@Override
	public String getStyleName() {
		return this.agendaConsulta.getStyleName();
	}

	@Override
	public boolean isAllDay() {
		return false;
	}

	@Override
	public void addEventChangeListener(EventChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeEventChangeListener(EventChangeListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void setCaption(String caption) {
		this.agendaConsulta.setCaption(caption);
        fireEventChange();
	}

	@Override
	public void setDescription(String description) {
		this.agendaConsulta.setDescription(description);		
        fireEventChange();
	}

	@Override
	public void setEnd(Date end) {
		this.agendaConsulta.setEnd(end);
        fireEventChange();
	}

	@Override
	public void setStart(Date start) {
		this.agendaConsulta.setStart(start);		
        fireEventChange();
	}

	@Override
	public void setStyleName(String styleName) {
		this.agendaConsulta.setStyleName(styleName);
        fireEventChange();
	}

	@Override
	public void setAllDay(boolean isAllDay) {
	}

	public AgendaConsulta getConsulta() {
		return agendaConsulta;
	}

	public void setConsulta(AgendaConsulta agendaConsulta) {
		this.agendaConsulta = agendaConsulta;
        fireEventChange();
	}
		
}
