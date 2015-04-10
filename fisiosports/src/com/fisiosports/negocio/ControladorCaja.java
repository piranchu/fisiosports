package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import com.fisiosports.modelo.entidades.caja.Caja;
import com.fisiosports.modelo.entidades.caja.Caja.Estado;
import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.fisiosports.modelo.entidades.pacientes.Paciente;

@Stateless
public class ControladorCaja implements ICaja{

	@PersistenceContext(unitName="fisiosportsPU")
	private EntityManager em;
	
	@Override
	public void crearMovimiento(Caja caja, Movimiento movimiento) {
		caja = em.merge(caja);
		em.lock(caja, LockModeType.WRITE);
		movimiento.setCaja(caja);
		movimiento = em.merge(movimiento);
		movimiento.ejecutar();
		caja.getMovimientos().add(movimiento);
		em.merge(caja);
		em.merge(movimiento);
		em.flush();
	}

	@Override
	public void borrarMovimiento(Long id) {
		System.out.println("[ControladorMovimientos.borrarMovimiento] borra movimiento "+id);
		Movimiento movimiento = em.find(Movimiento.class, id);
		if (movimiento != null){
			movimiento.setCaja(em.merge(movimiento.getCaja()));
			em.lock(movimiento.getCaja(), LockModeType.WRITE);
			movimiento.anular();
			em.remove(movimiento);
			em.flush();

		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> obtenerMovimientos(
			Class<? extends Movimiento> tipoMovimiento, Concepto concepto,
			ProductoServicio productoServicio, Paciente paciente, Date fechaInicial, Date fechaFinal) {
		
		return em.createNamedQuery("Movimiento.all").getResultList();
	}
	
	@Override
	public Movimiento obtenerMovimiento(Long id) {
		return em.find(Movimiento.class, id);
	}

	@Override
	public void crearConcepto(Concepto concepto) {
		em.merge(concepto);
		
	}

	@Override
	public void borrarConcepto(Concepto concepto) {
		concepto = em.merge(concepto);
		em.remove(concepto);
		em.flush();
	}

	@Override
	public Concepto obtenerConcepto(Long idConcepto) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Concepto> consultarConceptos() {
//		em.flush();
		return em.createNamedQuery("Concepto.all").getResultList();
	}

	@Override
	public void crearProductoServicio(ProductoServicio productoServicio) {
		em.merge(productoServicio);
	}

	@Override
	public void borrarProductoServicio(ProductoServicio productoServicio) {
		productoServicio = em.merge(productoServicio);
		em.remove(productoServicio);
		em.flush();
	}

	@Override
	public ProductoServicio obtenerProductoServicio(Long idProductoServicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductoServicio> consultarProductosServicios() {
		return em.createNamedQuery("ProductoServicio.all").getResultList();
	}

	@Override
	public CierreCaja cerrarCaja(Caja caja) throws Exception{
		if (caja.getEstado().equals(Estado.CERRADA)){
			throw new Exception("La caja ya está cerrada.");
		}
		caja = em.merge(caja);
		System.out.println("[ControladorCaja.cerrarCaja] cierre.caja:"+caja.getMovimientos().size());
		em.lock(caja, LockModeType.WRITE);
		CierreCaja cierre = caja.cerrar();
		System.out.println("[ControladorCaja.cerrarCaja] cierre.movimientos:"+cierre.getMovimientos().size());
		em.persist(cierre);
		em.merge(caja);
		em.flush();

		return cierre;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CierreCaja> obtenerCierresCaja() {
		
		return em.createNamedQuery("CuentaFinanciera.all").getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Movimiento> obtenerMovimientosActuales() {
		Caja caja = this.obtenerCaja();
		for (Movimiento movimiento:caja.getMovimientos()){
			Movimiento mov = movimiento;
		}
		return caja.getMovimientos();
	}

	@Override
	public Caja abrirCaja(Caja caja, Double saldoInicial) throws Exception {
		
		if (caja.getEstado().equals(Estado.ABIERTA)){
			throw new Exception("La caja ya está aberta.");
		}
		caja = em.merge(caja);
		em.lock(caja, LockModeType.WRITE);
		caja.abrir(saldoInicial);
		em.flush();

		return caja;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Caja obtenerCaja() {
		List<Caja> cajas = em.createNamedQuery("Caja.all").getResultList();
		Caja caja = null;
		if (cajas.isEmpty()){
			caja = new Caja();
			caja.setEstado(Estado.ABIERTA);
			em.persist(caja);
		}else{
			caja = cajas.get(0);
		}
		return caja;
	}
	
}
