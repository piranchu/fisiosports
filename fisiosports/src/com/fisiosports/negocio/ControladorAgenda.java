package com.fisiosports.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.fisiosports.modelo.entidades.ConsultaAgenda;

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
	public void agregarConsulta(ConsultaAgenda consultaAgenda) {
		em.getTransaction().begin();
		em.merge(consultaAgenda.getPaciente());
		em.persist(consultaAgenda);
		em.getTransaction().commit();
		
	}

	@Override
	public List<ConsultaAgenda> obtenerConsultas(Date start, Date end) {
		return this.em.createNamedQuery("ConsultaAgenda.findConsultasByDates")
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
	}

	@Override
	public void modificarConsulta(ConsultaAgenda consultaAgenda) {
		em.getTransaction().begin();
		em.merge(consultaAgenda);
		em.getTransaction().commit();
	}

	@Override
	public void borrarConsulta(ConsultaAgenda consultaAgenda) {
		em.getTransaction().begin();
		em.remove(consultaAgenda);
		em.getTransaction().commit();
	}

}
