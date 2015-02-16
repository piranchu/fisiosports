package com.fisiosports.web.ui.contenedores;

import java.util.Collection;

import com.fisiosports.web.ui.contenedores.beantypes.MovimientoDT;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorMovimientosCaja extends BeanItemContainer<MovimientoDT>{

	private static final long serialVersionUID = 1L;

	public ContenedorMovimientosCaja(Class<? super MovimientoDT> type,
			Collection<? extends MovimientoDT> collection)
			throws IllegalArgumentException {
		super(type, collection);
		this.addNestedContainerBean("movimiento");
		this.addNestedContainerBean("movimiento.cuentaFinanciera");
		this.addNestedContainerBean("movimiento.categoria");
		this.addNestedContainerBean("movimiento.paciente");
	}

	public static Object[] getColumnasVisibles(){
		return new Object[]{"icon","movimiento.cuentaFinanciera.nombre", "movimiento.categoria.nombre", "movimiento.importe", 
				"movimiento.moneda","movimiento.fecha", /*"movimiento.cuentaFinanciera.saldo", */"movimiento.observaciones", 
				"movimiento.paciente.documento", "movimiento.paciente.nombre", "movimiento.paciente.apellido", "deleteButton"};
	}

	public static String[] getNonbresColumnas(){
		return new String[]{"","cuenta", "categoria", "importe","moneda", "fecha", /*"saldo", */"observaciones", 
				"documento", "nombre", "apellido", ""};
	}
	
	
	
}
