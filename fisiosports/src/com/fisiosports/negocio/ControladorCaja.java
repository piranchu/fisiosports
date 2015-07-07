package com.fisiosports.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fisiosports.modelo.entidades.caja.Caja;
import com.fisiosports.modelo.entidades.caja.Caja.Estado;
import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.modelo.entidades.caja.Movimiento.TipoMovimiento;
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
//		System.out.println("[ControladorMovimientos.borrarMovimiento] borra movimiento "+id);
		Movimiento movimiento = em.find(Movimiento.class, id);
		if (movimiento != null){
			movimiento.setCaja(em.merge(movimiento.getCaja()));
			movimiento.getCaja().getMovimientos().remove(movimiento);
			em.lock(movimiento.getCaja(), LockModeType.WRITE);
			movimiento.anular();
			em.remove(movimiento);
			em.flush();

		}
		
	}
	@Override
	public void borrarMovimiento(Movimiento movimiento) {
//		System.out.println("[ControladorMovimientos.borrarMovimiento] movimiento "+movimiento);
		movimiento = em.merge(movimiento);
		if (movimiento != null){
			movimiento.setCaja(em.merge(movimiento.getCaja()));
			movimiento.getCaja().getMovimientos().remove(movimiento);
			em.merge(movimiento.getCaja());
			em.lock(movimiento.getCaja(), LockModeType.WRITE);
			movimiento.anular();
			em.remove(movimiento);
			em.flush();

		}
		
	}


	private List<Ingreso> obtenerMovimientosIngreso(Concepto concepto,
			ProductoServicio productoServicio, Paciente paciente, Date fechaInicial, Date fechaFinal){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ingreso> cq = cb.createQuery(Ingreso.class);
		Root<Ingreso> root = cq.from(Ingreso.class);
		
		List<Predicate> predicates = new LinkedList<Predicate>();
		if (concepto!=null){
			predicates.add(cb.equal(root.get("concepto"), concepto));
		}
		if (productoServicio!=null){
			predicates.add(cb.equal(root.get("productoServicio"), productoServicio));
		}
		if (paciente!=null){
			predicates.add(cb.equal(root.get("paciente"), paciente));
		}
		if (fechaInicial!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("fecha"), fechaInicial));
		}
		if (fechaFinal!=null){
			predicates.add(cb.lessThanOrEqualTo(root.<Date>get("fecha"), fechaFinal));
		}

		cq.select(root).where(predicates.toArray(new Predicate[]{}));
		
		return em.createQuery(cq).getResultList();
	}
	
	private List<Egreso> obtenerMovimientosEgreso(Concepto concepto,
			ProductoServicio productoServicio, Paciente paciente, Date fechaInicial, Date fechaFinal){

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Egreso> cq = cb.createQuery(Egreso.class);
		Root<Egreso> rootEgreso = cq.from(Egreso.class);
		
		List<Predicate> predicates = new LinkedList<Predicate>();
		
		if (concepto!=null){
			predicates.add(cb.equal(rootEgreso.get("concepto"), concepto));
		}
		if (productoServicio!=null){
			predicates.add(cb.equal(rootEgreso.get("productoServicio"), productoServicio));
		}
		if (paciente!=null){
			predicates.add(cb.equal(rootEgreso.get("paciente"), paciente));
		}
		if (fechaInicial!=null){
			predicates.add(cb.greaterThanOrEqualTo(rootEgreso.<Date>get("fecha"), fechaInicial));
		}
		if (fechaFinal!=null){
			predicates.add(cb.lessThanOrEqualTo(rootEgreso.<Date>get("fecha"), fechaFinal));
		}

		cq.select(rootEgreso).where(predicates.toArray(new Predicate[]{}));
		
		return em.createQuery(cq).getResultList();
	}
	
	
	@Override
	public List<? extends Movimiento> obtenerMovimientos(
			Class<? extends Movimiento> tipoMovimiento, Concepto concepto,
			ProductoServicio productoServicio, Paciente paciente, Date fechaInicial, Date fechaFinal) {
		
		List<Movimiento> movimientos;
		
		if (tipoMovimiento == Egreso.class){
			return this.obtenerMovimientosEgreso(
					concepto, productoServicio, paciente, fechaInicial, fechaFinal);
		}else if (tipoMovimiento  ==  Ingreso.class){
			return this.obtenerMovimientosIngreso(
					concepto, productoServicio, paciente, fechaInicial, fechaFinal);
		}else{
			List<Ingreso> ingresos = this.obtenerMovimientosIngreso(
					concepto, productoServicio, paciente, fechaInicial, fechaFinal);
			
			List<Egreso> egresos = this.obtenerMovimientosEgreso(
					concepto, productoServicio, paciente, fechaInicial, fechaFinal);
			movimientos = new LinkedList<Movimiento>();
			movimientos.addAll(ingresos);
			movimientos.addAll(egresos);
		}
		
		return movimientos;
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
	public List<Concepto> consultarConceptos(TipoMovimiento tipoMovimiento) {
		
		List<Concepto> todosConceptos = em.createNamedQuery("Concepto.all").getResultList();
		List<Concepto> conceptos = new LinkedList<Concepto>();
		
		for (Concepto concepto:todosConceptos){
			for (TipoMovimiento tipoMov: concepto.getTiposMovimiento()){
				if (tipoMov == tipoMovimiento){
					conceptos.add(concepto);
				}
			}
		}
		if (tipoMovimiento == null){
			return todosConceptos;
		}
		return conceptos;
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
	public List<ProductoServicio> consultarProductosServicios(Concepto concepto) {
	
		Query query;
		if (concepto != null){
			query = em.createNamedQuery("ProductoServicio.findByConcepto")
					.setParameter("idConcepto", concepto.getId());
		}else{
			query = em.createNamedQuery("ProductoServicio.all");
		}
		
		return query.getResultList();
	}

	@Override
	public CierreCaja cerrarCaja(Caja caja) throws Exception{
		if (caja.getEstado().equals(Estado.CERRADA)){
			throw new Exception("La caja ya está cerrada.");
		}
		caja = em.merge(caja);
//		System.out.println("[ControladorCaja.cerrarCaja] cierre.caja:"+caja.getMovimientos().size());
		em.lock(caja, LockModeType.WRITE);
		CierreCaja cierre = caja.cerrar();
//		System.out.println("[ControladorCaja.cerrarCaja] cierre.movimientos:"+cierre.getMovimientos().size());
		em.persist(cierre);
		em.merge(caja);
		em.flush();

		return cierre;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CierreCaja> obtenerCierresCaja() {
		
		return em.createNamedQuery("CierreCaja.all").getResultList();
	}
	
	@Override
	public CierreCaja cargarMovimientos(CierreCaja cierre){
		cierre = em.merge(cierre);
		for(Movimiento movimiento:cierre.getMovimientos()){
			Movimiento mov = movimiento;
//			System.out.println("[ControladorCaja.cargarMovimientosCaja] movimiento:"+mov);
		}
		return cierre;
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
	public Caja abrirCaja(Caja caja) throws Exception {
		
		if (caja.getEstado().equals(Estado.ABIERTA)){
			throw new Exception("La caja ya está aberta.");
		}
		caja = em.merge(caja);
		em.lock(caja, LockModeType.WRITE);
		caja.abrir();
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
