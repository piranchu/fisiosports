package com.fisiosports.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String diagnostico;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date fecha;
	private String indicaciones;
	@OneToOne 
	private Tratamiento tratamiento = new Tratamiento();
	
    @ManyToOne
    private Paciente paciente;
    
    public Evaluacion(){}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getIndicaciones() {
		return indicaciones;
	}
	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}
	public Tratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	
}
