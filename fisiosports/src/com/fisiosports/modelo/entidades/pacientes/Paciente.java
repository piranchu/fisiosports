package com.fisiosports.modelo.entidades.pacientes;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fisiosports.modelo.entidades.agenda.AgendaConsulta;

/**
 * Entity implementation class for Entity: Paciente
 * 
 */
@NamedQueries({
	
	@NamedQuery(name = "Paciente.all", query = "SELECT p FROM Paciente p "),
	@NamedQuery(name = "Paciente.findByDocument", query = "SELECT p FROM Paciente p where p.documento = :documento ")

})

@Entity
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private String documento;
	private String nombre = "";
	private String apellido = "";
	private String telefono = "";
	private String correoElectronico = "";
	private String emergenciaMedica = "";
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String ocupacion = "";
	@OneToMany (mappedBy="paciente", orphanRemoval=true, fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Evaluacion> evaluaciones = new LinkedList<Evaluacion>();
	
	@OneToMany (mappedBy="paciente", orphanRemoval=true, fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<AgendaConsulta> consultas;

	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEmergenciaMedica() {
		return emergenciaMedica;
	}

	public void setEmergenciaMedica(String emergenciaMedica) {
		this.emergenciaMedica = emergenciaMedica;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AgendaConsulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<AgendaConsulta> consultas) {
		this.consultas = consultas;
	}

}
