package com.fisiosports.negocio;

import java.util.List;

import javax.ejb.Local;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.Evaluacion;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.TerapiaFisica;
import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

@Local
public interface IPacientes {

	public void crearPaciente(Paciente paciente);
	public Paciente obtenerPaciente(Long documento);
	public List<Paciente> obtenerPacientes();
	public void borrarPaciente(Paciente paciente);
	
	public void agregarEvaluacionPaciente(Evaluacion evaluacion, Paciente paciente);
	public void borrarEvaluacion(Evaluacion evaluacion);
	public void agregarConsultaTratamiento(Consulta consulta, Evaluacion evaluacion);
	public void borrarConsultaTratamiento(Consulta consulta);
	public List<Consulta> obtenerConsultas(Long idEvaluacion);
	public Consulta obtenerConsulta(Long id);
	public List<TipoGimnasio> obtenerTipos(Gimnasio gimnasio);
	public List<TipoTerapiaFisica> obtenerTipos(TerapiaFisica terapia);
	
}
