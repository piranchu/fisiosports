package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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
		movimiento.setCuentaFinanciera(em.merge(movimiento.getCuentaFinanciera()));
		em.lock(movimiento.getCuentaFinanciera(), LockModeType.WRITE);
		movimiento.ejecutar();
		em.flush();
	}

	@Override
	public void borrarMovimiento(Long id) {
		System.out.println("[ControladorMovimientos.borrarMovimiento] borra movimiento "+id);
		Movimiento movimiento = em.find(Movimiento.class, id);
		if (movimiento != null){
			movimiento.setCuentaFinanciera(em.merge(movimiento.getCuentaFinanciera()));
			em.lock(movimiento.getCuentaFinanciera(), LockModeType.WRITE);
			movimiento.anular();
			em.flush();
			em.remove(movimiento);
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

	@Override
	public void crearCategoria(Categoria categoria) {
		em.merge(categoria);
		
	}

	@Override
	public void borrarCategoria(Categoria categoria) {
		categoria = em.merge(categoria);
		em.remove(categoria);
		em.flush();
	}

	@Override
	public Categoria obtenerCategoria(Long idCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> consultarCategorias() {
//		em.flush();
		return em.createNamedQuery("Categoria.all").getResultList();
	}

	@Override
	public void crearCuentaFinanciera(CuentaFinanciera cuenta) {
		em.merge(cuenta);
	}

	@Override
	public void borrarCuentaFinanciera(CuentaFinanciera cuenta) {
		cuenta = em.merge(cuenta);
		em.remove(cuenta);
		em.flush();
	}

	@Override
	public CuentaFinanciera obtenerCuentaFinanciera(Long idCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaFinanciera> consultarCuentasFinancieras() {
		return em.createNamedQuery("CuentaFinanciera.all").getResultList();
	}

}
