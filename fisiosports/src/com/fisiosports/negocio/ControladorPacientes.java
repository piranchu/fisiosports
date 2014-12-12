package com.fisiosports.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fisiosports.modelo.entidades.Paciente;

@Stateless
public class ControladorPacientes implements IPacientes{

	@PersistenceContext(unitName="fisiosportsPU")
	private EntityManager em;

	@Override
	public void crearPaciente(Paciente paciente) {
		em.merge(paciente);
		
	}

	@Override
	public Paciente obtenerPaciente(Long documento){
		Paciente paciente = null;
		paciente = em.find(Paciente.class, documento);
		return paciente;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> obtenerPacientes() {
		return em.createNamedQuery("Paciente.all").getResultList();
	}

	
}
