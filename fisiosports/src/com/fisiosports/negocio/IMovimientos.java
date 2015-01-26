package com.fisiosports.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.caja.Categoria;
import com.fisiosports.modelo.entidades.caja.CuentaFinanciera;
import com.fisiosports.modelo.entidades.caja.Movimiento;

@Local
public interface IMovimientos {
	
	public void crearMovimiento(Movimiento movimiento);
	public void borrarMovimiento(Long id);
	public List<Movimiento> obtenerMovimientos(Class<? extends Movimiento> tipoMovimiento, 
			Categoria categoria, CuentaFinanciera cuentaFinanciera, 
			Paciente paciente, Date fechaInicial, Date fechaFinal);
	public Movimiento obtenerMovimiento(Long id);
	
	
	public void crearCategoria(Categoria categoria);
	public void borrarCategoria(Categoria categoria);
	public Categoria obtenerCategoria(Long idCategoria);
	public List<Categoria> consultarCategorias();
	
	public void crearCuentaFinanciera(CuentaFinanciera cuenta);
	public void borrarCuentaFinanciera(CuentaFinanciera cuenta);
	public CuentaFinanciera obtenerCuentaFinanciera(Long idCuenta);
	public List<CuentaFinanciera> consultarCuentasFinancieras();

}
