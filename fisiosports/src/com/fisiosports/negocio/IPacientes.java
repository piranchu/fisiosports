package com.fisiosports.negocio;

import java.util.List;

import com.fisiosports.modelo.entidades.Paciente;

public interface IPacientes {

	public void crearPaciente(Paciente paciente);
	
	public List<Paciente> obtenerPacientes();

	public Paciente obtenerPaciente(Long documento);
	
}
