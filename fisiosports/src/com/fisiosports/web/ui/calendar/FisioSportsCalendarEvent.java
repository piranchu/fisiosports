package com.fisiosports.web.ui.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fisiosports.modelo.entidades.ConsultaAgenda;
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
	private ConsultaAgenda consultaAgenda;
    private transient List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();
	
	public FisioSportsCalendarEvent(ConsultaAgenda consultaAgenda){
		this.consultaAgenda = consultaAgenda;
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
		return this.consultaAgenda.getStart();
	}

	@Override
	public Date getEnd() {
		// TODO Auto-generated method stub
		return this.consultaAgenda.getEnd();
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return this.consultaAgenda.getCaption();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.consultaAgenda.getDescription();
	}

	@Override
	public String getStyleName() {
		return this.consultaAgenda.getStyleName();
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
		this.consultaAgenda.setCaption(caption);
        fireEventChange();
	}

	@Override
	public void setDescription(String description) {
		this.consultaAgenda.setDescription(description);		
        fireEventChange();
	}

	@Override
	public void setEnd(Date end) {
		this.consultaAgenda.setEnd(end);
        fireEventChange();
	}

	@Override
	public void setStart(Date start) {
		this.consultaAgenda.setStart(start);		
        fireEventChange();
	}

	@Override
	public void setStyleName(String styleName) {
		this.consultaAgenda.setStyleName(styleName);
        fireEventChange();
	}

	@Override
	public void setAllDay(boolean isAllDay) {
	}

	public ConsultaAgenda getConsulta() {
		return consultaAgenda;
	}

	public void setConsulta(ConsultaAgenda consultaAgenda) {
		this.consultaAgenda = consultaAgenda;
        fireEventChange();
	}
		
}
