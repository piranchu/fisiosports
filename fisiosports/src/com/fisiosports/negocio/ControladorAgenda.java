package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fisiosports.modelo.entidades.agenda.AgendaConsulta;
import com.fisiosports.modelo.entidades.pacientes.Paciente;

@Stateless
public class ControladorAgenda implements IAgenda{

	@PersistenceContext(unitName="fisiosportsPU")
	private EntityManager em;
	
	public ControladorAgenda(){
	}
	
	@Override
	public void agregarConsulta(AgendaConsulta agendaConsulta) {
		agendaConsulta.setPaciente(em.getReference(Paciente.class, agendaConsulta.getPaciente().getDocumento()));
		em.persist(agendaConsulta);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgendaConsulta> obtenerConsultas(Date start, Date end) {
		return this.em.createNamedQuery("AgendaConsulta.findConsultasByDates")
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
	}

	@Override
	public void modificarConsulta(AgendaConsulta agendaConsulta) {
		em.merge(agendaConsulta);
	}

	@Override
	public void borrarConsulta(AgendaConsulta agendaConsulta) {
		em.remove(em.merge(agendaConsulta));
	}

}
