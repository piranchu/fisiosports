package com.fisiosports.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.fisiosports.modelo.entidades.AgendaConsulta;
import com.fisiosports.modelo.entidades.Paciente;

public class ControladorAgenda implements IAgenda, Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager em;
	static private ControladorAgenda instance; 
	
	private ControladorAgenda(){
		em = Persistence.createEntityManagerFactory("fisiosports-pu").createEntityManager();
	}
	
	static public ControladorAgenda getInstance(){
		if (instance == null)
			instance = new ControladorAgenda();
		return instance;
	}

	@Override
	public void agregarConsulta(AgendaConsulta agendaConsulta) {
		em.getTransaction().begin();
		agendaConsulta.setPaciente(em.getReference(Paciente.class, agendaConsulta.getPaciente().getDocumento()));
		em.persist(agendaConsulta);
		em.getTransaction().commit();
		
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
		em.getTransaction().begin();
		em.merge(agendaConsulta);
		em.getTransaction().commit();
	}

	@Override
	public void borrarConsulta(AgendaConsulta agendaConsulta) {
		em.getTransaction().begin();
		em.remove(agendaConsulta);
		em.getTransaction().commit();
	}

}
