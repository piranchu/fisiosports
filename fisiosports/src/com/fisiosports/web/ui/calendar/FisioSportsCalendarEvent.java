package com.fisiosports.web.ui.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IAgenda;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeListener;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeNotifier;

public class FisioSportsCalendarEvent implements EditableCalendarEvent, EventChangeNotifier {

	private static final long serialVersionUID = 1L;
	
	private IAgenda agenda;
	private Consulta consulta;
    private transient List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();
	
	public FisioSportsCalendarEvent(Consulta consulta){
		this.consulta = consulta;
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
		return this.consulta.getStart();
	}

	@Override
	public Date getEnd() {
		// TODO Auto-generated method stub
		return this.consulta.getEnd();
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return this.consulta.getCaption();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.consulta.getDescription();
	}

	@Override
	public String getStyleName() {
		return this.consulta.getStyleName();
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
		this.consulta.setCaption(caption);
        fireEventChange();
	}

	@Override
	public void setDescription(String description) {
		this.consulta.setDescription(description);		
        fireEventChange();
	}

	@Override
	public void setEnd(Date end) {
		this.consulta.setEnd(end);
        fireEventChange();
	}

	@Override
	public void setStart(Date start) {
		this.consulta.setStart(start);		
        fireEventChange();
	}

	@Override
	public void setStyleName(String styleName) {
		this.consulta.setStyleName(styleName);
        fireEventChange();
	}

	@Override
	public void setAllDay(boolean isAllDay) {
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
        fireEventChange();
	}
		
}
