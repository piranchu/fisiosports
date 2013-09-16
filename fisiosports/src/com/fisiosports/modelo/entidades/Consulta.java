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

@Entity
@NamedQueries({
    @NamedQuery(name="Consulta.findConsultasByDates",
                query="SELECT c FROM Consulta c "
                		+ "WHERE c.start >= :start AND c.end <= :end "
                		+ "ORDER BY c.start ")
}) 


public class Consulta implements Serializable{

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	//@ManyToOne 	private Paciente paciente;
	private String paciente;
	@OneToOne
	private TerapiaFisica terapiaFisica;
	@OneToOne
	private Gimnasio gimnasio;
	@OneToOne
	private Quinesiologia quinesiologia;
    private String caption;
    private String description;
    private Date end;
    private Date start;
    private String styleName;
	
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
	}
	public Gimnasio getGimnasio() {
		return gimnasio;
	}
	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}
	public Quinesiologia getQuinesiologia() {
		return quinesiologia;
	}
	public void setQuinesiologia(Quinesiologia quinesiologia) {
		this.quinesiologia = quinesiologia;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	
	
	
}
