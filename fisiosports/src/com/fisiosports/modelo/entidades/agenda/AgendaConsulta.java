package com.fisiosports.modelo.entidades.agenda;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fisiosports.modelo.entidades.pacientes.Paciente;

@NamedQueries({
    @NamedQuery(name="AgendaConsulta.findConsultasByDates",
                query="SELECT c FROM AgendaConsulta c "
                		+ "WHERE c.start >= :start "
                		+ "AND c.end <= :end "
                		+ "ORDER BY c.start ")
}) 
@Entity

public class AgendaConsulta implements Serializable{

    // Atributos propios de clase Calendar
	private String caption;
    private String description;
    private Date end;
    private Date start;
    private String styleName;
    
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	// Atributos propios de AgendaConsulta 
	@ManyToOne //(cascade = CascadeType.PERSIST) 
	private Paciente paciente;
	private Boolean terapiaFisica;
	private Boolean gimnasio;
	private Boolean masajes;
    private Boolean nutricionista;
    private Boolean deportologo;
    private Boolean traumatologo;
    private Boolean psicologo;
    private String observaciones;
	
	private static final long serialVersionUID = 1L;

	public AgendaConsulta() {
	}   

	public AgendaConsulta(String caption, String description, Date date) {
        this.caption = caption;
        this.description = description;
        this.start = date;
        this.end = date;
    }	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getTerapiaFisica() {
		return terapiaFisica;
		
	}
	public void setTerapiaFisica(Boolean terapiaFisica) {
		this.terapiaFisica = terapiaFisica;
	}
	public Boolean getGimnasio() {
		return gimnasio;
	}
	public void setGimnasio(Boolean gimnasio) {
		this.gimnasio = gimnasio;
	}
	public Boolean getMasajes() {
		return masajes;
	}
	public void setMasajes(Boolean masajes) {
		this.masajes = masajes;
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

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		if (terapiaFisica && masajes){
			this.styleName = "cyan";
		}else if (terapiaFisica){
			this.styleName = "yellow";
		}else if (gimnasio){
			this.styleName = "purple";
		}else if (masajes){
			this.styleName = "blue";
		}else if(nutricionista){
			this.styleName = "pink";
		}else if(traumatologo){
			this.styleName = "orange";
		}else if(deportologo){
			this.styleName = "grey";
		}else if(psicologo){
			this.styleName = "skyblue";
		}else{
			this.styleName = "green";
		}
		
	}

	public Boolean getPsicologo() {
		return psicologo;
	}

	public void setPsicologo(Boolean psicologo) {
		this.psicologo = psicologo;
	}		
	
}
