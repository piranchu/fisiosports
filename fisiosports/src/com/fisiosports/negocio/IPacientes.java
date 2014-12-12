package com.fisiosports.negocio;

import java.util.List;

import javax.ejb.Local;

import com.fisiosports.modelo.entidades.Paciente;

@Local
public interface IPacientes {

	public void crearPaciente(Paciente paciente);
	
	public List<Paciente> obtenerPacientes();

	public Paciente obtenerPaciente(Long documento);
	
	
}
