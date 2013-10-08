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

    // Atributos propios de clase Calendar
	private String caption;
    private String description;
    private Date end;
    private Date start;
    private String styleName;
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	// Atributos propios de Consulta 
	@ManyToOne private Paciente paciente;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TerapiaFisica terapiaFisica;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Gimnasio gimnasio;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Quinesiologia quinesiologia;
    private Boolean nutricionista;
    private Boolean deportologo;
    private Boolean traumatologo;
    private String observaciones;
	
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
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

	public Boolean getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Boolean nutricionista) {
		this.nutricionista = nutricionista;
	}

	public Boolean getDeportologo() {
		return deportologo;
	}

	public void setDeportologo(Boolean deportologo) {
		this.deportologo = deportologo;
	}

	public Boolean getTraumatologo() {
		return traumatologo;
	}

	public void setTraumatologo(Boolean traumatologo) {
		this.traumatologo = traumatologo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}	
	
}
