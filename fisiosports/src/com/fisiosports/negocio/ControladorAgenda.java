package com.fisiosports.negocio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.fisiosports.modelo.entidades.Consulta;

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
	public void agregarConsulta(Consulta consulta) {
		em.getTransaction().begin();
		em.persist(consulta);
		em.getTransaction().commit();
		
	}

	@Override
	public List<Consulta> obtenerConsultas(Date start, Date end) {
		return this.em.createNamedQuery("Consulta.findConsultasByDates")
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
	}

	@Override
	public void modificarConsulta(Consulta consulta) {
		em.getTransaction().begin();
		em.merge(consulta);
		em.getTransaction().commit();
	}

	
	
	
}
