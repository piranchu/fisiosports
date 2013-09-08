package com.fisiosports.negocio;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.fisiosports.modelo.entidades.Cliente;

public class Clientes implements IClientes{

	private EntityManager em;
	static private Clientes instance; 
	
	private Clientes(){
		em = Persistence.createEntityManagerFactory("fisiosports-pu").createEntityManager();
	}
	
	static public Clientes getInstance(){
		if (instance == null)
			instance = new Clientes();
		return instance;
	}
	
	@Override
	public void crearCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		System.out.println("[Clientes.crearCliente] empieza transaccion");
		em.getTransaction().begin();
		System.out.println("[Clientes.crearCliente] persist:"+cliente.getId());
		em.persist(cliente);
		System.out.println("[Clientes.crearCliente] commit transaccion");
		em.getTransaction().commit();
		System.out.println("[Clientes.crearCliente] FIN");
		
	}
	
	
	
}
