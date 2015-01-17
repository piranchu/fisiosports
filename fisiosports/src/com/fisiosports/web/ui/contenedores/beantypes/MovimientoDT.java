package com.fisiosports.web.ui.contenedores.beantypes;

import java.util.LinkedList;
import java.util.List;

import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Movimiento;

public class MovimientoDT extends Movimiento{

	private String tipo;
	private Double ingreso;
	private Double egreso;
	
	public MovimientoDT(Movimiento movimiento){
		if (movimiento instanceof Ingreso){
			tipo = "ingreso";
			ingreso = movimiento.getImporte();
			egreso = 0.0;
		}else if (movimiento instanceof Egreso){
			tipo = "egreso";
			ingreso = 0.0;
			egreso = movimiento.getImporte();
		}
	}
	
	static public List<MovimientoDT> buildList(List<? extends Movimiento> listMovimiento){
		
		List<MovimientoDT> list = new LinkedList<>();
		for (Movimiento movimiento:listMovimiento){
			MovimientoDT movDT = new MovimientoDT(movimiento);
			list.add(movDT);
		}
		
		return list;
		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getIngreso() {
		return ingreso;
	}

	public void setIngreso(Double ingreso) {
		this.ingreso = ingreso;
	}

	public Double getEgreso() {
		return egreso;
	}

	public void setEgreso(Double egreso) {
		this.egreso = egreso;
	}
	
	
}
