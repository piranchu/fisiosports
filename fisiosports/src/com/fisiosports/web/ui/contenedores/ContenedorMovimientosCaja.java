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
		this.addNestedContainerBean("cuentaFinanciera");
		this.addNestedContainerBean("categoria");
	}

	public static Object[] getColumnasVisibles(){
		return new Object[]{"tipo","cuentaFinanciera.nombre","categoria.nombre","ingreso","egreso","moneda","fecha"};
	}

	public static String[] getNonbresColumnas(){
		return new String[]{"tipo","cuenta","categoria","ingreso","egreso","moneda","fecha"};
	}
	
	
	
}
