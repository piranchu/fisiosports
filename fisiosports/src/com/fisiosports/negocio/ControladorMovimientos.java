package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.caja.Categoria;
import com.fisiosports.modelo.entidades.caja.CuentaFinanciera;
import com.fisiosports.modelo.entidades.caja.Movimiento;

@Stateless
public class ControladorMovimientos implements IMovimientos{

	@PersistenceContext(unitName="fisiosportsPU")
	private EntityManager em;
	
	@Override
	public void crearMovimiento(Movimiento movimiento) {
		em.persist(movimiento);
		
	}

	@Override
	public void borrarMovimiento(Long id) {
		System.out.println("[ControladorMovimientos.borrarMovimiento] borra movimiento "+id);
		Movimiento movimiento = em.find(Movimiento.class, id);
		if (movimiento != null){
			System.out.println("[ControladorMovimientos.borrarMovimiento] se encontro movimiento, se borra");
			em.remove(movimiento);
		}else{
			System.out.println("[ControladorMovimientos.borrarMovimiento] NO se encontro");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> obtenerMovimientos(
			Class<? extends Movimiento> tipoMovimiento, Categoria categoria,
			CuentaFinanciera cuentaFinanciera, Paciente paciente, Date fechaInicial, Date fechaFinal) {
		
		return em.createNamedQuery("Movimiento.all").getResultList();
	}
	
	@Override
	public Movimiento obtenerMovimiento(Long id) {
		return em.find(Movimiento.class, id);
	}

}
