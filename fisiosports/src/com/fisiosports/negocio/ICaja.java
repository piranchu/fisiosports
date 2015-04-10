package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.fisiosports.modelo.entidades.caja.Caja;
import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.fisiosports.modelo.entidades.pacientes.Paciente;

@Local
public interface ICaja {
	
	public void crearMovimiento(Caja caja, Movimiento movimiento);
	public void borrarMovimiento(Long id);
	public List<Movimiento> obtenerMovimientos(Class<? extends Movimiento> tipoMovimiento, 
			Concepto concepto, ProductoServicio productoServicio, 
			Paciente paciente, Date fechaInicial, Date fechaFinal);
	public Movimiento obtenerMovimiento(Long id);
	
	
	public void crearProductoServicio(ProductoServicio productoServicio);
	public void borrarProductoServicio(ProductoServicio productoServicio);
	public ProductoServicio obtenerProductoServicio(Long idCategoria);
	public List<ProductoServicio> consultarProductosServicios();
	
	public void crearConcepto(Concepto concepto);
	public void borrarConcepto(Concepto concepto);
	public Concepto obtenerConcepto(Long idCuenta);
	public List<Concepto> consultarConceptos();
	
	// Devuelve excepcion si la caja está cerrada. Genera movimiento de apertura por el valor indicado
	public Caja obtenerCaja();
	public Caja abrirCaja(Caja caja, Double importe) throws Exception;
	public CierreCaja cerrarCaja(Caja caja) throws Exception;
	public List<CierreCaja> obtenerCierresCaja();
	public List<Movimiento> obtenerMovimientosActuales();
	
}
