package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeEvent;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeListener;
import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeNotifier;

/**
 * Entity implementation class for Entity: Consulta
 *
 */
@Entity

public class Consulta implements Serializable, EditableCalendarEvent, EventChangeNotifier {

	@Id
	private Long id;
	@OneToOne
	private TerapiaFisica terapiaFisica;
	@OneToOne
	private Gimnasio gimansio;
	@OneToOne
	private Quinesiologia quinesiologia;
    private String caption;
    private String description;
    private Date end;
    private Date start;
    private String styleName;
    private transient List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();
	
	private static final long serialVersionUID = 1L;

	public Consulta() {
		super();		
	}   

	public Consulta(String caption, String description, Date date) {
        this.caption = caption;
        this.description = description;
        start = date;
        end = date;
    }	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public TerapiaFisica getTerapiaFisica() {
		return terapiaFisica;
		
	}
	public void setTerapiaFisica(TerapiaFisica terapiaFisica) {
		this.terapiaFisica = terapiaFisica;
        fireEventChange();

	}
	public Gimnasio getGimansio() {
		return gimansio;
	}
	public void setGimansio(Gimnasio gimansio) {
		this.gimansio = gimansio;
        fireEventChange();
	}
	public Quinesiologia getQuinesiologia() {
		return quinesiologia;
	}
	public void setQuinesiologia(Quinesiologia quinesiologia) {
		this.quinesiologia = quinesiologia;
        fireEventChange();
	}
    @Override
	public String getCaption() {
		return caption;
	}
    @Override
	public void setCaption(String caption) {
		this.caption = caption;
        fireEventChange();
	}
    @Override
	public String getDescription() {
		return description;
	}
    @Override
	public void setDescription(String description) {
		this.description = description;
        fireEventChange();
	}
    @Override
	public Date getEnd() {
		return end;
	}
    @Override
	public void setEnd(Date end) {
		this.end = end;
        fireEventChange();
	}
    @Override
	public Date getStart() {
		return start;
	}
    @Override
	public void setStart(Date start) {
		this.start = start;
        fireEventChange();
	}
    @Override
	public String getStyleName() {
		return styleName;
	}
    @Override
	public void setStyleName(String styleName) {
		this.styleName = styleName;
        fireEventChange();
	}
	
    @Override
    public void addEventChangeListener(EventChangeListener listener) {
        listeners.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventChangeNotifier
     * #removeListener
     * (com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventChangeListener
     * )
     */
    @Override
    public void removeEventChangeListener(EventChangeListener listener) {
        listeners.remove(listener);
    }

    /**
     * Fires an event change event to the listeners. Should be triggered when
     * some property of the event changes.
     */
    protected void fireEventChange() {
        EventChangeEvent event = new EventChangeEvent(this);

        for (EventChangeListener listener : listeners) {
            listener.eventChange(event);
        }
    }

	@Override
	public boolean isAllDay() {
		return false;
	}

	@Override
	public void setAllDay(boolean isAllDay) {
	}
   
	
	
}
