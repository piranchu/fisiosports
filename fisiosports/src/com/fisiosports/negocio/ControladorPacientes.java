package com.fisiosports.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.Tratamiento;

public class ControladorPacientes implements IPacientes{

	private EntityManager em;
	static private ControladorPacientes instance; 
	
	private ControladorPacientes(){
		em = Persistence.createEntityManagerFactory("fisiosports-pu").createEntityManager();
	}
	
	static public ControladorPacientes getInstance(){
		if (instance == null)
			instance = new ControladorPacientes();
		return instance;
	}
	
	@Override
	public void crearPaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		//System.out.println("[ControladorPacientes.crearPaciente] empieza transaccion");
		em.getTransaction().begin();
		//System.out.println("[ControladorPacientes.crearPaciente] persist:"+paciente.getDocumento());
		em.merge(paciente);
		//System.out.println("[ControladorPacientes.crearPaciente] commit transaccion");
		em.getTransaction().commit();
		//System.out.println("[ControladorPacientes.crearPaciente] FIN");
		
	}

	@Override
	public Paciente obtenerPaciente(Long documento){
		Paciente paciente = null;
		System.out.println("[ControladorPacientes.obtenerPaciente] documento="+documento);
		paciente = em.find(Paciente.class, documento);
		System.out.println("[ControladorPacientes.obtenerPaciente] paciente="+paciente);
		if (paciente != null){
			System.out.println("[ControladorPacientes.obtenerPaciente] paciente.nombre="+paciente.getNombre());
		}
		return paciente;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> obtenerPacientes() {
		return em.createNamedQuery("Paciente.all").getResultList();
	}

	
}
