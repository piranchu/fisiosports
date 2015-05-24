package com.fisiosports.negocio;

import java.util.List;

import javax.ejb.Local;

import com.fisiosports.modelo.entidades.pacientes.Consulta;
import com.fisiosports.modelo.entidades.pacientes.Evaluacion;
import com.fisiosports.modelo.entidades.pacientes.Gimnasio;
import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.modelo.entidades.pacientes.TerapiaFisica;
import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;

@Local
public interface IPacientes {

	public void crearPaciente(Paciente paciente);
	public Paciente obtenerPaciente(Long id);
	public List<Paciente> obtenerPacientes();
	public void borrarPaciente(Paciente paciente) throws Exception;
	
	public void agregarEvaluacionPaciente(Evaluacion evaluacion, Paciente paciente);
	public void borrarEvaluacion(Evaluacion evaluacion);
	public void agregarConsultaTratamiento(Consulta consulta, Evaluacion evaluacion);
	public void borrarConsultaTratamiento(Consulta consulta);
	public List<Consulta> obtenerConsultas(Long idEvaluacion);
	public Consulta obtenerConsulta(Long id);
	public List<TipoGimnasio> obtenerTipos(Gimnasio gimnasio);
	public List<TipoTerapiaFisica> obtenerTipos(TerapiaFisica terapia);
	
}
