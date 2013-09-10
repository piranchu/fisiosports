package com.fisiosports.negocio;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.fisiosports.modelo.entidades.Consulta;

public class ControladorAgenda implements IAgenda{

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

	
	
	
}
