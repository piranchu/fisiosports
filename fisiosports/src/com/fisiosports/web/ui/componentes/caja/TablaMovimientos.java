package com.fisiosports.web.ui.componentes.caja;

import java.util.List;

import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.web.ui.contenedores.ContenedorMovimientosCaja;
import com.fisiosports.web.ui.contenedores.beantypes.BeanItemMovimiento;
import com.vaadin.ui.Table;

public class TablaMovimientos extends Table{

	private static final long serialVersionUID = 1L;
	private List<? extends Movimiento> movimientos;
	
	public TablaMovimientos(List<? extends Movimiento> movimientos){
		
		this.movimientos = movimientos;
		if (this.movimientos == null || this.movimientos.isEmpty()){
			return;
		}
		ContenedorMovimientosCaja contenedor = new ContenedorMovimientosCaja(
				BeanItemMovimiento.class);

		for(Movimiento movimiento:movimientos){
			contenedor.addItem(new BeanItemMovimiento(movimiento, null));
		}
		this.setContainerDataSource(contenedor);
		this.setVisibleColumns(ContenedorMovimientosCaja.getColumnasVisiblesConsulta());
		this.setColumnHeaders(ContenedorMovimientosCaja.getNonbresColumnasConsulta());
		this.setPageLength(contenedor.size());
	}
	

}
